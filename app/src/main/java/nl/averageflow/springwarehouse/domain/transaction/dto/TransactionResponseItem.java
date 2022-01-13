package nl.averageflow.springwarehouse.domain.transaction.dto;

import nl.averageflow.springwarehouse.domain.product.dto.ProductResponseItem;
import nl.averageflow.springwarehouse.domain.user.dto.UserResponseItem;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.UUID;

public record TransactionResponseItem(
        UUID uid,
        Collection<ProductResponseItem> products,
        UserResponseItem user,
        Timestamp createdAt) {
}