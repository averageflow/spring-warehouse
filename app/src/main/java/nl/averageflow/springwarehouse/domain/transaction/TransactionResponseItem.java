package nl.averageflow.springwarehouse.domain.transaction;

import nl.averageflow.springwarehouse.domain.product.dto.ProductResponseItem;
import nl.averageflow.springwarehouse.domain.user.UserResponseItem;

import java.util.Set;
import java.util.UUID;

public record TransactionResponseItem(
        UUID uid,
        Set<ProductResponseItem>,
        UserResponseItem user
) {
}