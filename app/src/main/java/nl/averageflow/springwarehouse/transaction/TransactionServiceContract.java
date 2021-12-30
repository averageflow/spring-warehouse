package nl.averageflow.springwarehouse.transaction;

import nl.averageflow.springwarehouse.transaction.model.Transaction;
import nl.averageflow.springwarehouse.product.dto.SellProductsRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface TransactionServiceContract {
    Page<Transaction> getTransactions(final Pageable pageable);

    Optional<Transaction> getTransactionByUid(final UUID uid);

    Transaction createTransaction(final SellProductsRequest request);
}
