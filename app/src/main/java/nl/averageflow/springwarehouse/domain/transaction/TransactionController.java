package nl.averageflow.springwarehouse.domain.transaction;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@CrossOrigin
public final class TransactionController {

    private final TransactionServiceContract transactionService;

    public TransactionController(final TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/api/transactions")
    public Page<TransactionResponseItem> getTransactions(final Pageable pageable) {
        return this.transactionService.getTransactions(pageable);
    }

    @GetMapping("/api/transactions/{uid}")
    public TransactionResponseItem getTransaction(@PathVariable final UUID uid) {
        return this.transactionService.getTransactionByUid(uid);
    }
}
