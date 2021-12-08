package nl.averageflow.springwarehouse.requests;

public final class AddArticlesRequestItem {
    private long itemId;
    private String name;
    private long stock;

    protected AddArticlesRequestItem() {
    }

    public long getItemId() {
        return this.itemId;
    }


    public String getName() {
        return this.name;
    }


    public long getStock() {
        return this.stock;
    }

}
