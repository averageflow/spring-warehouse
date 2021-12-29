package nl.averageflow.springwarehouse.requests;

import java.util.UUID;

public record SellProductsRequestItem(UUID itemUid, long amountOf) {
}
