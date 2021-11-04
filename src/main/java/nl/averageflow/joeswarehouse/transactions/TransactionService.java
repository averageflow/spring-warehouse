package nl.averageflow.joeswarehouse.transactions;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
