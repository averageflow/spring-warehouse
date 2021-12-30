package nl.averageflow.springwarehouse.domain.product.dto;

import nl.averageflow.springwarehouse.domain.category.dto.CategoryResponseItem;
import nl.averageflow.springwarehouse.domain.product.model.ArticleAmountInProduct;

import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

public record ProductResponseItem(
        UUID uid,
        String name,
        Iterable<String> imageURLs,
        Double price,
        CategoryResponseItem categoryResponseItem,
        Timestamp createdAt,
        Timestamp updatedAt,
        Set<ArticleAmountInProduct> articleAmountInProductSet) {
}
