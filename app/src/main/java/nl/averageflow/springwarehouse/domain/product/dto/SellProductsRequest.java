package nl.averageflow.springwarehouse.domain.product.dto;

import javax.validation.constraints.NotEmpty;

public record SellProductsRequest(@NotEmpty Iterable<SellProductsRequestItem> wantedItemsForSale) {
}
