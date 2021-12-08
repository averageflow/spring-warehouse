package nl.averageflow.springwarehouse.services;

import nl.averageflow.springwarehouse.models.Transaction;
import nl.averageflow.springwarehouse.repositories.TransactionRepository;
import nl.averageflow.springwarehouse.responses.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public final class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public TransactionResponse getTransactions() {
        return new TransactionResponse(this.transactionRepository.findAll());
    }

    public Optional<Transaction> getTransactionByUid(UUID uid) {
        return this.transactionRepository.findByUid(uid);
    }
}
