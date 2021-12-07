package nl.averageflow.joeswarehouse.requests;

import java.util.List;

public final class AddArticlesRequest {
    private List<AddArticlesRequestItem> inventory;

    protected AddArticlesRequest() {
    }

    public List<AddArticlesRequestItem> getInventory() {
        return this.inventory;
    }

}