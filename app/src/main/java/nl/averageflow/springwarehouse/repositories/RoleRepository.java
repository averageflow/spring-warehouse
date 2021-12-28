package nl.averageflow.springwarehouse.repositories;

import nl.averageflow.springwarehouse.models.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends CrudRepository<Role, UUID> {

    Optional<Role> findByItemName(String itemName);
}
