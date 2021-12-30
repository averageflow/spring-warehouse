package nl.averageflow.springwarehouse.domain.product.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public record AddProductsRequestArticleItem(@NotNull UUID uid, @Min(1) long amountOf) {
}
