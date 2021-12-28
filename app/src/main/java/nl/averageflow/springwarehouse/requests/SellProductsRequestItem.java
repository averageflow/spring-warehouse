package nl.averageflow.springwarehouse.requests;

import java.util.UUID;

public final class SellProductsRequestItem {
    private UUID itemUid;
    private long amountOf;


    public UUID getItemUid() {
        return itemUid;
    }

    public long getAmountOf() {
        return amountOf;
    }
}
