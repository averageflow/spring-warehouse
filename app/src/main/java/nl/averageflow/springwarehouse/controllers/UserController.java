package nl.averageflow.springwarehouse.controllers;

import nl.averageflow.springwarehouse.models.User;
import nl.averageflow.springwarehouse.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserService userService;


    @GetMapping("/api/users")
    public Page<User> getUsers(final Pageable pageable) {
        return this.userService.getUsers(pageable);
    }

    @GetMapping("/api/users/{uid}")
    public Optional<User> getUser(@PathVariable final UUID uid) {
        return this.userService.getUserByUid(uid);
    }
}
