package nl.averageflow.springwarehouse.domain.authentication.service;

import nl.averageflow.springwarehouse.domain.authentication.dto.RegisterRequest;
import nl.averageflow.springwarehouse.domain.user.model.User;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<String> authenticateUser(final String email, final String password);

    ResponseEntity<String> registerUser(final RegisterRequest registerRequest);

    ResponseEntity<String> forgotPassword(final String email, final String url);

    ResponseEntity<String> resetPassword(final User user);
}
