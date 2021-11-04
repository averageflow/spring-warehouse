package nl.averageflow.joeswarehouse.products;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    Optional<Product> findById(Long id);

    Set<Product> findAll();
}
