package nl.averageflow.springwarehouse.domain.transaction.service;

import nl.averageflow.springwarehouse.domain.product.dto.SellProductsRequest;
import nl.averageflow.springwarehouse.domain.transaction.dto.TransactionResponseItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

import java.util.UUID;

public interface TransactionService {
    Page<TransactionResponseItem> getTransactions(final Pageable pageable);

    TransactionResponseItem getTransactionByUid(final UUID uid);

    TransactionResponseItem createTransaction(final SellProductsRequest request, final Authentication authentication);
}
