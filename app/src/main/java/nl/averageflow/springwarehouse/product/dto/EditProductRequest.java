package nl.averageflow.springwarehouse.product.dto;

import java.util.UUID;

public record EditProductRequest(
        String name,
        Double price,
        Iterable<String> imageURLs,
        UUID categoryUid) {
}
