package nl.averageflow.springwarehouse.requests;

import java.util.UUID;

public record EditProductRequest(
        String name,
        Double price,
        Iterable<String> imageURLs,
        UUID categoryUid) {
}
