package nl.averageflow.joeswarehouse.responses;

import nl.averageflow.joeswarehouse.models.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public final class ProductResponse {
    private final HashMap<Long, Product> data;

    private final List<Long> sort;

    public ProductResponse(Set<Product> data) {
        HashMap<Long, Product> dataMap = new HashMap<>();
        List<Long> dataSort = new ArrayList<>();

        for (Product product : data) {
            dataMap.put(product.getId(), product);
            dataSort.add(product.getId());
        }

        this.data = dataMap;
        this.sort = dataSort;
    }

    public HashMap<Long, Product> getData() {
        return this.data;
    }

    public List<Long> getSort() {
        return this.sort;
    }
}
