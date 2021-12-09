package nl.averageflow.springwarehouse.requests;

public final class AddArticlesRequestItem {
    private long itemId;
    private String name;
    private long stock;

    protected AddArticlesRequestItem() {
    }

    public AddArticlesRequestItem(long itemId, String name, long stock) {
        this.itemId = itemId;
        this.name = name;
        this.stock = stock;
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
