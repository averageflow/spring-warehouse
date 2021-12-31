package nl.averageflow.springwarehouse.domain.product.dto;

import java.util.Collection;

import javax.validation.constraints.NotEmpty;

public record SellProductsRequest(@NotEmpty Collection<SellProductsRequestItem> wantedItemsForSale) {
}
