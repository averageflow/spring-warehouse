package nl.averageflow.springwarehouse.domain.authentication.service;

import nl.averageflow.springwarehouse.domain.authentication.dto.RegisterRequest;
import nl.averageflow.springwarehouse.domain.user.UserRole;
import nl.averageflow.springwarehouse.domain.user.model.Role;
import nl.averageflow.springwarehouse.domain.user.model.User;
import nl.averageflow.springwarehouse.domain.user.repository.RoleRepository;
import nl.averageflow.springwarehouse.domain.user.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username")
    private String senderMail;

    public AuthServiceImpl(final AuthenticationManager authenticationManager,
                           final UserRepository userRepository,
                           final RoleRepository roleRepository,
                           final PasswordEncoder passwordEncoder){
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<String> authenticateUser(final String email, final String password) {
        final Authentication authToken = new UsernamePasswordAuthenticationToken(email, password);

        try {
            final Authentication authentication = this.authenticationManager.authenticate(authToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return new ResponseEntity<>("User authenticated successfully!", HttpStatus.OK);
        } catch (final Exception e) {
            return new ResponseEntity<>("User could not be authenticated!", HttpStatus.FORBIDDEN);
        }
    }

    public ResponseEntity<String> registerUser(final RegisterRequest registerRequest) {
        if (this.userRepository.existsByEmail(registerRequest.email())) {
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }

        final var user = new User();

        user.setItemName(registerRequest.name());
        user.setEmail(registerRequest.email());
        user.setPassword(this.passwordEncoder.encode(registerRequest.password()));

        final Optional<Role> role = this.roleRepository.findByItemName(UserRole.READ_ONLY);

        if (role.isEmpty()) {
            return new ResponseEntity<>("No suitable roles found for user! Check your database for roles!", HttpStatus.BAD_REQUEST);
        }

        user.setRole(role.get());

        this.userRepository.save(user);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }

	@Override
	public ResponseEntity<String> forgotPassword(final String email, final String url) {

        return userRepository.findByEmail(email)
                .map(user -> this.sendPasswordResetLink(user, url))
                .orElseGet(() -> new ResponseEntity<>("FAILURE", HttpStatus.NOT_FOUND));
	}

    @Override
    public ResponseEntity<String> resetPassword(final User userIn) {

        return userRepository.findByToken(userIn.getToken())
                .map(user -> this.validateAndResetPassword(user, userIn.getPassword()))
                .orElseGet(() -> new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST));
    }

    private ResponseEntity<String> validateAndResetPassword(User user, String newPassword) {

        if (StringUtils.isEmpty(newPassword)) {
            return new ResponseEntity<>("Password cannot be empty", HttpStatus.BAD_REQUEST);
        }
        user.setPassword((passwordEncoder.encode(newPassword)));
        user.setToken(StringUtils.EMPTY);
        userRepository.save(user);

        return new ResponseEntity<>("Password reset successfully", HttpStatus.OK);
    }

    private ResponseEntity<String> sendPasswordResetLink(User user, String url) {
        String token = UUID.randomUUID().toString();
        user.setToken(token);

        String msg = getMailBody(url, token);

        try {
            MimeMessage mimeMessage = getMimeMessage(user, msg);
            javaMailSender.send(mimeMessage);

            userRepository.save(user);

            return new ResponseEntity<>("Password reset link has been sent to your email. ", HttpStatus.OK);
        } catch (MailException | MessagingException e) {
            e.printStackTrace();
            return new ResponseEntity<>("FAILURE", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private MimeMessage getMimeMessage(User user, String msg) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

        mimeMessageHelper.setFrom(senderMail);
        mimeMessageHelper.setTo(user.getEmail());
        mimeMessageHelper.setSubject("Password reset request");
        mimeMessageHelper.setText(msg, true);
        return mimeMessage;
    }

    private String getMailBody(String url, String token) {
        String anchorStart = StringUtils.join("<a href=", url, "/reset-password?token=", token, ">");
        String anchorEnd = "</a>";
        return StringUtils.join("Click ", anchorStart, "here", anchorEnd, " to reset your password");
    }
}
