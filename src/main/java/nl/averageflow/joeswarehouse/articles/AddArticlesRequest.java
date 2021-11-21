package nl.averageflow.joeswarehouse.articles;

import java.util.List;

public class AddArticlesRequest {
    private List<AddArticlesRequestItem> inventory;

    public List<AddArticlesRequestItem> getInventory() {
        return this.inventory;
    }

    public void setInventory(List<AddArticlesRequestItem> inventory) {
        this.inventory = inventory;
    }

    protected AddArticlesRequest() {
    }

}