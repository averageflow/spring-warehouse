package nl.averageflow.springwarehouse.services;

import nl.averageflow.springwarehouse.models.Transaction;
import nl.averageflow.springwarehouse.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public final class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public Page<Transaction> getTransactions(Pageable pageable) {
        return this.transactionRepository.findAll(pageable);
    }

    public Optional<Transaction> getTransactionByUid(UUID uid) {
        return this.transactionRepository.findByUid(uid);
    }
}
