package nl.averageflow.springwarehouse.services;

import nl.averageflow.springwarehouse.requests.RegisterRequest;
import org.springframework.http.ResponseEntity;

public interface AuthServiceContract {
    ResponseEntity<String> authenticateUser(final String email, final String password);

    ResponseEntity<String> registerUser(final RegisterRequest registerRequest);
}
