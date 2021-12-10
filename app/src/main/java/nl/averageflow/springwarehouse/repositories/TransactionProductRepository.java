package nl.averageflow.springwarehouse.repositories;

import nl.averageflow.springwarehouse.models.TransactionProduct;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface TransactionProductRepository extends CrudRepository<TransactionProduct, UUID> {
    @NonNull
    Set<TransactionProduct> findAll();
}
