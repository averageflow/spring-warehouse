package nl.averageflow.joeswarehouse.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import nl.averageflow.joeswarehouse.models.Transaction;
import nl.averageflow.joeswarehouse.responses.TransactionResponse;
import nl.averageflow.joeswarehouse.services.TransactionService;

@RestController
@CrossOrigin
public final class TransactionController {
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
