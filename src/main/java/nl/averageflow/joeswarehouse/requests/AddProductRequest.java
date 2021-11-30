package nl.averageflow.joeswarehouse.requests;

import java.util.List;

import nl.averageflow.joeswarehouse.models.Product;

public final class AddProductRequest {

    private List<Product> products;

    public List<Product> getProducts() {
        return this.products;
    }

    protected AddProductRequest() {
    }
}
