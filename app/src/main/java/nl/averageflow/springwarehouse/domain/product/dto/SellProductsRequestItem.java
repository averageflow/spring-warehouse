package nl.averageflow.springwarehouse.domain.product.dto;

import java.util.UUID;

public record SellProductsRequestItem(UUID itemUid, long amountOf) {
}
