package nl.averageflow.springwarehouse.domain.product.dto;

import javax.validation.constraints.NotEmpty;
import java.util.Collection;

public record SellProductsRequest(@NotEmpty Collection<SellProductsRequestItem> wantedItemsForSale) {
}
