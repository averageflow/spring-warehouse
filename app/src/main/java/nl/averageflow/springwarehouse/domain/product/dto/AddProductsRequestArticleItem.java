package nl.averageflow.springwarehouse.domain.product.dto;

import java.util.UUID;

public record AddProductsRequestArticleItem(UUID uid, long amountOf) {
}
