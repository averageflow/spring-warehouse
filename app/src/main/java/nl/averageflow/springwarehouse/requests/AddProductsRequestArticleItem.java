package nl.averageflow.springwarehouse.requests;

import java.util.UUID;

public record AddProductsRequestArticleItem(UUID uid, long amountOf) {
}
