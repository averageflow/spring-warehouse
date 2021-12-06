package nl.averageflow.joeswarehouse.requests;

import nl.averageflow.joeswarehouse.models.Product;

import java.sql.Timestamp;
import java.util.List;

public final class AddProductRequest {

    private List<Product> products;

    protected AddProductRequest() {
    }

    public List<Product> getProducts() {
        return this.products;
    }

    public void setProducts(List<Product> products) {
        for (Product product : products
        ) {
            product.setCreatedAt(new Timestamp(1L));
        }

        this.products = products;
    }


}
