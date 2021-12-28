package nl.averageflow.springwarehouse.requests;

public final class AddCategoriesRequest {
    private Iterable<AddCategoriesRequestItem> items;

    public Iterable<AddCategoriesRequestItem> getItems() {
        return this.items;
    }

}