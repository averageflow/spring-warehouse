package nl.averageflow.joeswarehouse.requests;

import java.util.List;

public final class AddProductsRequestItem {
    private Long itemId;
    private String name;
    private Double price;
    private List<AddProductsRequestArticleItem> containArticles;


    protected AddProductsRequestItem() {

    }

    public Long getItemId() {
        return this.itemId;
    }

    public Double getPrice() {
        return this.price;
    }

    public String getName() {
        return this.name;
    }

    public List<AddProductsRequestArticleItem> getContainArticles() {
        return containArticles;
    }
}
