package nl.averageflow.springwarehouse.requests;

import java.util.UUID;

public class EditProductRequest {
    private String name;

    private Double price;

    private Iterable<String> imageURLs;

    private UUID categoryUid;

    public Double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public Iterable<String> getImageURLs() {
        return imageURLs;
    }

    public UUID getCategoryUid() {
        return categoryUid;
    }
}
