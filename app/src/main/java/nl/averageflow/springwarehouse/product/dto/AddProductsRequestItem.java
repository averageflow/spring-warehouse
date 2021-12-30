package nl.averageflow.springwarehouse.product.dto;

import java.util.UUID;

public record AddProductsRequestItem(
        String name,
        Double price,
        UUID categoryUid,
        Iterable<AddProductsRequestArticleItem> containArticles,
        Iterable<String> imageURLs) {
}
