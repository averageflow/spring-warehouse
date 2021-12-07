package nl.averageflow.joeswarehouse.requests;

public final class AddProductsRequestArticleItem {
    private Long itemId;
    private Long amountOf;

    protected AddProductsRequestArticleItem() {
    }

    public Long getItemId() {
        return itemId;
    }

    public Long getAmountOf() {
        return amountOf;
    }
}
