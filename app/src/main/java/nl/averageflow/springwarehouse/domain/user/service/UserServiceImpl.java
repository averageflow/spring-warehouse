package nl.averageflow.springwarehouse.domain.user.service;

import nl.averageflow.springwarehouse.domain.user.dto.UpdateUserRequest;
import nl.averageflow.springwarehouse.domain.user.dto.UserResponseItem;
import nl.averageflow.springwarehouse.domain.user.model.User;
import nl.averageflow.springwarehouse.domain.user.repository.RoleRepository;
import nl.averageflow.springwarehouse.domain.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserServiceImpl(final UserRepository userRepository, final RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
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

    @Transactional
    public ResponseEntity<String> deleteUserByUid(final UUID uid) {
        this.userRepository.deleteByUid(uid);
        return new ResponseEntity<>("User deleted successfully!", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> updateUserRole(final UpdateUserRequest request) {
        final var email = request.email();
        return this.userRepository.findByEmail(email)
                .map(u -> this.updateRole(u, request.role()))
                .orElseThrow(() -> new UsernameNotFoundException("could not find email: " + email));

    }

    private ResponseEntity<String> updateRole(final User user, final String roleRequested) {
        final var role = this.roleRepository.findByItemName(roleRequested.toUpperCase())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        user.setRole(role);
        this.userRepository.save(user);
        return new ResponseEntity<>("User's role updated successfully!", HttpStatus.OK);
    }

    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        final User user = this.userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("could not find email: " + email));

        return new nl.averageflow.springwarehouse.domain.authentication.dto.UserDetails(user);
    }
}
