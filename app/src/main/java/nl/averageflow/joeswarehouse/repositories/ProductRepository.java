package nl.averageflow.joeswarehouse.repositories;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import nl.averageflow.joeswarehouse.models.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    Optional<Product> findById(Long id);

    Set<Product> findAll();
}
