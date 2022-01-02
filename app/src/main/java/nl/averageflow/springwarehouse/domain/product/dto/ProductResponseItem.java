package nl.averageflow.springwarehouse.domain.product.dto;

import nl.averageflow.springwarehouse.domain.category.dto.CategoryResponseItem;
import nl.averageflow.springwarehouse.domain.product.model.ArticleAmountInProduct;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.UUID;

public record ProductResponseItem(
        UUID uid,
        String name,
        Collection<String> imageURLs,
        Double price,
        long stock,
        CategoryResponseItem category,
        Timestamp createdAt,
        Timestamp updatedAt,
        Collection<ArticleAmountInProduct> articles) {
}
