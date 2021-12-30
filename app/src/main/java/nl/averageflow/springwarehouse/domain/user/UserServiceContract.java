package nl.averageflow.springwarehouse.domain.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface UserServiceContract {
    Page<UserResponseItem> getUsers(final Pageable pageable);

    UserResponseItem getUserByUid(final UUID uid);

    void deleteUserByUid(final UUID uid);
}
