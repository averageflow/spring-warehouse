package nl.averageflow.joeswarehouse.repositories;

import nl.averageflow.joeswarehouse.models.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    @NonNull
    Optional<Transaction> findById(@NonNull Long id);

    @NonNull
    Set<Transaction> findAll();
}
