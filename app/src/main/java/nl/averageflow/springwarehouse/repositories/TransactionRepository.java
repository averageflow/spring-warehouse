package nl.averageflow.springwarehouse.repositories;

import nl.averageflow.springwarehouse.models.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, UUID> {

    @NonNull
    Optional<Transaction> findByUid(@NonNull UUID uid);

    @NonNull
    Set<Transaction> findAll();

    void deleteByUid(@NonNull UUID uid);
}
