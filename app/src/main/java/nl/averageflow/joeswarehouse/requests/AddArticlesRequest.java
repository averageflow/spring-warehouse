package nl.averageflow.joeswarehouse.requests;

public final class AddArticlesRequest {
    private Iterable<AddArticlesRequestItem> inventory;

    protected AddArticlesRequest() {
    }

    public Iterable<AddArticlesRequestItem> getInventory() {
        return this.inventory;
    }

}