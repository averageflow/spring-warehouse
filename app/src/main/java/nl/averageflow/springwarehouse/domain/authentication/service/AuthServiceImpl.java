package nl.averageflow.springwarehouse.domain.authentication.service;

import nl.averageflow.springwarehouse.domain.authentication.dto.RegisterRequest;
import nl.averageflow.springwarehouse.domain.user.UserRole;
import nl.averageflow.springwarehouse.domain.user.model.Role;
import nl.averageflow.springwarehouse.domain.user.model.User;
import nl.averageflow.springwarehouse.domain.user.repository.RoleRepository;
import nl.averageflow.springwarehouse.domain.user.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

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
}
