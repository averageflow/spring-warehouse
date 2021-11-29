package nl.averageflow.joeswarehouse.requests;

import java.util.List;

public final class AddArticlesRequest {
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