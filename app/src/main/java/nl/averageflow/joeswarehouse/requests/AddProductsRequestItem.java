package nl.averageflow.joeswarehouse.requests;

import java.util.List;

public final class AddProductsRequestItem {
    private String itemId;
    private String name;
    private List<AddProductsRequestArticleItem> containArticles;


    protected AddProductsRequestItem() {

    }

    public String getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public List<AddProductsRequestArticleItem> getContainArticles() {
        return containArticles;
    }
}
