package nl.averageflow.springwarehouse.requests;

public class EditProductRequest {
    private String name;

    private Double price;

    private Iterable<String> imageURLs;

    public Double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public Iterable<String> getImageURLs() {
        return imageURLs;
    }
}
