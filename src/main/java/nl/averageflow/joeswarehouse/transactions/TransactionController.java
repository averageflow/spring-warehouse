package nl.averageflow.joeswarehouse.transactions;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping("/api/transactions")
    public TransactionResponse getTransactions() {
        return this.transactionService.getTransactions();
    }

    @GetMapping("/api/transactions/{id}")
    public Optional<Transaction> getTransaction(@PathVariable Long id) {
        return this.transactionService.getTransactionByID(id);
    }
}
