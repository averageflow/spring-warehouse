package nl.averageflow.springwarehouse.domain.product.dto;

import java.util.UUID;

public record EditProductRequest(
        String name,
        Double price,
        Iterable<String> imageURLs,
        UUID categoryUid) {
}
