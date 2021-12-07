package nl.averageflow.joeswarehouse.requests;

public final class AddProductsRequestArticleItem {
    private String itemId;
    private String amountOf;

    protected AddProductsRequestArticleItem() {
    }

    public String getItemId() {
        return itemId;
    }

    public String getAmountOf() {
        return amountOf;
    }
}
