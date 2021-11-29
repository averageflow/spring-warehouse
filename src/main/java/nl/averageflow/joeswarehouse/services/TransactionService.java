package nl.averageflow.joeswarehouse.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nl.averageflow.joeswarehouse.models.Transaction;
import nl.averageflow.joeswarehouse.repositories.TransactionRepository;
import nl.averageflow.joeswarehouse.responses.TransactionResponse;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    public TransactionResponse getTransactions() {
        return new TransactionResponse(this.transactionRepository.findAll());
    }

    public Optional<Transaction> getTransactionByID(Long id) {
        return this.transactionRepository.findById(id);
    }
}
