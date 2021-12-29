package nl.averageflow.springwarehouse.controllers;

import nl.averageflow.springwarehouse.requests.LoginRequest;
import nl.averageflow.springwarehouse.requests.RegisterRequest;
import nl.averageflow.springwarehouse.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public final class AuthController {

    @Autowired
    private AuthService authService;


    @PostMapping("/api/auth/authenticate")
    public ResponseEntity<String> authenticateUser(@RequestBody final LoginRequest loginRequest) {
        return this.authService.authenticateUser(loginRequest.email(), loginRequest.password());
    }

    @PostMapping("/api/auth/register")
    public ResponseEntity<String> registerUser(@RequestBody final RegisterRequest registerRequest) {
        return this.authService.registerUser(registerRequest);
    }
}