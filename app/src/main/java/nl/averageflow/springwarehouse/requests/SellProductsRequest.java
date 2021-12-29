package nl.averageflow.springwarehouse.requests;

public record SellProductsRequest(Iterable<SellProductsRequestItem> wantedItemsForSale) {
}
