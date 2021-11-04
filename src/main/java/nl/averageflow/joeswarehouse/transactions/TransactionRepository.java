package nl.averageflow.joeswarehouse.transactions;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    Optional<Transaction> findById(Long id);

    Set<Transaction> findAll();
}
