package nl.averageflow.joeswarehouse.repositories;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import nl.averageflow.joeswarehouse.models.Transaction;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    Optional<Transaction> findById(Long id);

    Set<Transaction> findAll();
}
