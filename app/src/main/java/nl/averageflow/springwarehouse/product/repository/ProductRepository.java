package nl.averageflow.springwarehouse.product.repository;

import nl.averageflow.springwarehouse.product.model.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, UUID> {

    @NonNull
    Optional<Product> findByUid(@NonNull UUID uid);

    @NonNull
    Set<Product> findAll();

    @Transactional
    void deleteByUid(@NonNull UUID uid);
}
