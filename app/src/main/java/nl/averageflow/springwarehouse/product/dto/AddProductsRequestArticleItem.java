package nl.averageflow.springwarehouse.product.dto;

import java.util.UUID;

public record AddProductsRequestArticleItem(UUID uid, long amountOf) {
}
