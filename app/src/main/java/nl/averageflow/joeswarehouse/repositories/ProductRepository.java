package nl.averageflow.joeswarehouse.repositories;

import nl.averageflow.joeswarehouse.models.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    @NonNull
    Optional<Product> findById(@NonNull Long id);

    @NonNull
    Set<Product> findAll();
}
