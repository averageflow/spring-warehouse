package nl.averageflow.springwarehouse.requests;

public final class AddProductRequest {

    private Iterable<AddProductsRequestItem> products;

    public Iterable<AddProductsRequestItem> getProducts() {
        return this.products;
    }

}
