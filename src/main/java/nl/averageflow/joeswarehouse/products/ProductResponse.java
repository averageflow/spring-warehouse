package nl.averageflow.joeswarehouse.products;

import java.util.List;

public class ProductResponse {
    private List<Product> data;

    public List<Product> getData() {
        return this.data;
    }

    public void setData(List<Product> data) {
        this.data = data;
    }

    public ProductResponse(List<Product> data) {
        this.setData(data);
    }
}
