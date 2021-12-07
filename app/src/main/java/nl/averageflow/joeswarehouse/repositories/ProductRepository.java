package nl.averageflow.joeswarehouse.repositories;

import nl.averageflow.joeswarehouse.models.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface ProductRepository extends CrudRepository<Product, UUID> {
    @NonNull
    Optional<Product> findByItemId(@NonNull long itemId);

    @NonNull
    Optional<Product> findByUid(@NonNull UUID uid);

    @NonNull
    Set<Product> findAll();

    @Transactional
    void deleteByUid(@NonNull UUID uid);
}
