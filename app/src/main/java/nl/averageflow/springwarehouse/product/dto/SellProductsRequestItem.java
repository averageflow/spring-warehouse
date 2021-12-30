package nl.averageflow.springwarehouse.product.dto;

import java.util.UUID;

public record SellProductsRequestItem(UUID itemUid, long amountOf) {
}
