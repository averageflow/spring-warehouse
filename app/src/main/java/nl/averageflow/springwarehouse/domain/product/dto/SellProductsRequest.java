package nl.averageflow.springwarehouse.domain.product.dto;

public record SellProductsRequest(Iterable<SellProductsRequestItem> wantedItemsForSale) {
}
