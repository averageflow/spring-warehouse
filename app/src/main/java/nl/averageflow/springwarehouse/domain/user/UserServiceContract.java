package nl.averageflow.springwarehouse.domain.user;

import nl.averageflow.springwarehouse.domain.user.dto.UpdateUserRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface UserServiceContract {
    Page<UserResponseItem> getUsers(final Pageable pageable);

    UserResponseItem getUserByUid(final UUID uid);

    void deleteUserByUid(final UUID uid);

    ResponseEntity<String> updateUserRole(UpdateUserRequest request);
}
