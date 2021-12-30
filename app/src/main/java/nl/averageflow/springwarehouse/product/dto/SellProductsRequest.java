package nl.averageflow.springwarehouse.product.dto;

public record SellProductsRequest(Iterable<SellProductsRequestItem> wantedItemsForSale) {
}
