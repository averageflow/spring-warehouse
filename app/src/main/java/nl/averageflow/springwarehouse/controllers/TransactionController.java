package nl.averageflow.springwarehouse.controllers;

import nl.averageflow.springwarehouse.models.Transaction;
import nl.averageflow.springwarehouse.responses.TransactionResponse;
import nl.averageflow.springwarehouse.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin
public final class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @GetMapping("/api/transactions")
    public Page<Transaction> getTransactions(Pageable pageable) {
        return this.transactionService.getTransactions(pageable);
    }

    @GetMapping("/api/transactions/{uid}")
    public Optional<Transaction> getTransaction(@PathVariable UUID uid) {
        return this.transactionService.getTransactionByUid(uid);
    }
}
