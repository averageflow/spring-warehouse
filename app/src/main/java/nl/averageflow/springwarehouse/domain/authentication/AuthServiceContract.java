package nl.averageflow.springwarehouse.domain.authentication;

import nl.averageflow.springwarehouse.domain.authentication.dto.RegisterRequest;
import nl.averageflow.springwarehouse.domain.user.model.User;
import org.springframework.http.ResponseEntity;

public interface AuthServiceContract {
    ResponseEntity<String> authenticateUser(final String email, final String password);

    ResponseEntity<String> registerUser(final RegisterRequest registerRequest);

    ResponseEntity<String> forgotPassword(final String email, String url);

    ResponseEntity<String> resetPassword(User user);
}
