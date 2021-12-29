package nl.averageflow.springwarehouse.requests;

import java.util.UUID;

public record AddProductsRequestItem(
        String name,
        Double price,
        UUID categoryUid,
        Iterable<AddProductsRequestArticleItem> containArticles,
        Iterable<String> imageURLs) {
}
