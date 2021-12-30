package nl.averageflow.springwarehouse.user.repository;

import nl.averageflow.springwarehouse.user.model.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends CrudRepository<Role, UUID> {

    Optional<Role> findByItemName(String itemName);
}
