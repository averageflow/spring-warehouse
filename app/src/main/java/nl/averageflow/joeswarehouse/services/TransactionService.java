package nl.averageflow.joeswarehouse.services;

import nl.averageflow.joeswarehouse.models.Transaction;
import nl.averageflow.joeswarehouse.repositories.TransactionRepository;
import nl.averageflow.joeswarehouse.responses.TransactionResponse;
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
