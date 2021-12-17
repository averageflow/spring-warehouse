package nl.averageflow.springwarehouse.requests;

public final class AddCategoriesRequest {
    private Iterable<AddCategoriesRequestItem> items;

    protected AddCategoriesRequest() {
    }

    public Iterable<AddCategoriesRequestItem> getItems() {
        return this.items;
    }

}