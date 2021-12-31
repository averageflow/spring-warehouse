package nl.averageflow.springwarehouse.domain.transaction;

import nl.averageflow.springwarehouse.domain.product.dto.SellProductsRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

import java.util.UUID;

public interface TransactionServiceContract {
    Page<TransactionResponseItem> getTransactions(final Pageable pageable);

    TransactionResponseItem getTransactionByUid(final UUID uid);

    TransactionResponseItem createTransaction(final SellProductsRequest request, final Authentication authentication);
}
