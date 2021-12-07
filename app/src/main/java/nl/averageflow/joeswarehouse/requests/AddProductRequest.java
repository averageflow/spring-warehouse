package nl.averageflow.joeswarehouse.requests;

import java.util.List;

public final class AddProductRequest {

    private List<AddProductsRequestItem> products;

    protected AddProductRequest() {
    }

    public List<AddProductsRequestItem> getProducts() {
        return this.products;
    }
    
}
