package nl.averageflow.springwarehouse.requests;

import java.util.UUID;

public final class AddProductsRequestArticleItem {
    private UUID uid;
    private long amountOf;


    public UUID getUid() {
        return uid;
    }

    public long getAmountOf() {
        return amountOf;
    }
}
