package nl.averageflow.springwarehouse.requests;

public class EditProductRequest {
    private String name;

    private Double price;

    public Double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}
