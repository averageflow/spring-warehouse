package nl.averageflow.springwarehouse.requests;

public final class SellProductsRequest {
    private Iterable<SellProductsRequestItem> wantedItemsForSale;

    public Iterable<SellProductsRequestItem> getWantedItemsForSale() {
        return this.wantedItemsForSale;
    }
}
