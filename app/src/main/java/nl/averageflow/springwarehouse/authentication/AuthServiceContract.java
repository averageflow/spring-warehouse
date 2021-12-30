package nl.averageflow.springwarehouse.authentication;

import nl.averageflow.springwarehouse.authentication.dto.RegisterRequest;
import org.springframework.http.ResponseEntity;

public interface AuthServiceContract {
    ResponseEntity<String> authenticateUser(final String email, final String password);

    ResponseEntity<String> registerUser(final RegisterRequest registerRequest);
}
