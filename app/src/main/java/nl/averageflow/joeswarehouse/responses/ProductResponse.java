package nl.averageflow.joeswarehouse.responses;

import nl.averageflow.joeswarehouse.models.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

public final class ProductResponse {
    private final HashMap<UUID, Product> data;

    private final Iterable<UUID> sort;

    public ProductResponse(Set<Product> data) {
        HashMap<UUID, Product> dataMap = new HashMap<>();
        ArrayList<UUID> dataSort = new ArrayList<>();

        for (Product product : data) {
            dataMap.put(product.getUid(), product);
            dataSort.add(product.getUid());
        }

        this.data = dataMap;
        this.sort = dataSort;
    }

    public HashMap<UUID, Product> getData() {
        return this.data;
    }

    public Iterable<UUID> getSort() {
        return this.sort;
    }
}
