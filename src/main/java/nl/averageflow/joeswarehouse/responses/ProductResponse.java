package nl.averageflow.joeswarehouse.responses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import nl.averageflow.joeswarehouse.models.Product;

public final class ProductResponse {
    private HashMap<Long, Product> data;

    private List<Long> sort;

    public HashMap<Long, Product> getData() {
        return this.data;
    }

    public void setData(HashMap<Long, Product> data) {
        this.data = data;
    }

    public List<Long> getSort() {
        return this.sort;
    }

    public void setSort(List<Long> sort) {
        this.sort = sort;
    }

    public ProductResponse(Set<Product> data) {
        HashMap<Long, Product> dataMap = new HashMap<Long, Product>();
        List<Long> dataSort = new ArrayList<Long>();

        for (Product product : data) {
            dataMap.put(product.getId(), product);
            dataSort.add(product.getId());
        }

        this.setData(dataMap);
        this.setSort(dataSort);
    }
}
