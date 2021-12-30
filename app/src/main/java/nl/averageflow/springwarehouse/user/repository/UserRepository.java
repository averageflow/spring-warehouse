package nl.averageflow.springwarehouse.user.repository;

import nl.averageflow.springwarehouse.user.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface UserRepository extends PagingAndSortingRepository<User, UUID> {
    @NonNull
    Optional<User> findByUid(@NonNull UUID uid);

    @NonNull
    Set<User> findAll();

    void deleteByUid(@NonNull UUID uid);

    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);
}
