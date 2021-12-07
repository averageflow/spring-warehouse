package nl.averageflow.joeswarehouse.requests;

public final class AddProductsRequestArticleItem {
    private long itemId;
    private long amountOf;

    protected AddProductsRequestArticleItem() {
    }

    public long getItemId() {
        return itemId;
    }

    public long getAmountOf() {
        return amountOf;
    }
}
