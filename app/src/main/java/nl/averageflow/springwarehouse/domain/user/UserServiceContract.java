package nl.averageflow.springwarehouse.domain.user;

import nl.averageflow.springwarehouse.domain.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface UserServiceContract {
    Page<User> getUsers(final Pageable pageable);

    Optional<User> getUserByUid(final UUID uid);

    void deleteUserByUid(final UUID uid);
}
