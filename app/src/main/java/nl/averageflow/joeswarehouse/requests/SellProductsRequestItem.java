package nl.averageflow.joeswarehouse.requests;

public final class SellProductsRequestItem {
    private String itemUid;
    private Long amountOf;

    protected SellProductsRequestItem() {
    }


    public String getItemUid() {
        return itemUid;
    }

    public Long getAmountOf() {
        return amountOf;
    }
}
