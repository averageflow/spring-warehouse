package nl.averageflow.joeswarehouse.requests;

public final class AddProductRequest {

    private Iterable<AddProductsRequestItem> products;

    protected AddProductRequest() {
    }

    public Iterable<AddProductsRequestItem> getProducts() {
        return this.products;
    }

}
