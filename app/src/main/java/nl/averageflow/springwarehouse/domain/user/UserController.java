package nl.averageflow.springwarehouse.domain.user;

import nl.averageflow.springwarehouse.domain.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin
public class UserController {

    private final UserServiceContract userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/users")
    public Page<User> getUsers(final Pageable pageable) {
        return this.userService.getUsers(pageable);
    }

    @GetMapping("/api/users/{uid}")
    public Optional<User> getUser(@PathVariable final UUID uid) {
        return this.userService.getUserByUid(uid);
    }
}
