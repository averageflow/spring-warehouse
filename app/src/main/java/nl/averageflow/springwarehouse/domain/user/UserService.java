package nl.averageflow.springwarehouse.domain.user;

import nl.averageflow.springwarehouse.domain.user.model.User;
import nl.averageflow.springwarehouse.domain.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class UserService implements UserDetailsService, UserServiceContract {

    private final UserRepository userRepository;

    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Page<UserResponseItem> getUsers(final Pageable pageable) {
        final Page<User> page = this.userRepository.findAll(pageable);

        return page.map(user -> new UserResponseItem(
                user.getUid(),
                user.getItemName(),
                user.getEmail(),
                user.getRole().getItemName(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        ));
    }

    public UserResponseItem getUserByUid(final UUID uid) {
        final User user = this.userRepository.findByUid(uid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return new UserResponseItem(
                user.getUid(),
                user.getItemName(),
                user.getEmail(),
                user.getRole().getItemName(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    public void deleteUserByUid(final UUID uid) {
        this.userRepository.deleteByUid(uid);
    }

    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        final User user = this.userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("could not find email: " + email));

        return new nl.averageflow.springwarehouse.domain.authentication.dto.UserDetails(user);
    }
}
