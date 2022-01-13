package nl.averageflow.springwarehouse.domain.user.service;

import nl.averageflow.springwarehouse.domain.user.dto.UpdateUserRequest;
import nl.averageflow.springwarehouse.domain.user.dto.UserResponseItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface UserService {
    Page<UserResponseItem> getUsers(final Pageable pageable);

    UserResponseItem getUserByUid(final UUID uid);

    ResponseEntity<String> deleteUserByUid(final UUID uid);

    ResponseEntity<String> updateUserRole(UpdateUserRequest request);
}
