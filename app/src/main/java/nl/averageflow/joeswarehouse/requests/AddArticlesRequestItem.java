package nl.averageflow.joeswarehouse.requests;

public final class AddArticlesRequestItem {
    private Long itemId;
    private String name;
    private Long stock;

    protected AddArticlesRequestItem() {
    }

    public Long getItemId() {
        return this.itemId;
    }


    public String getName() {
        return this.name;
    }


    public Long getStock() {
        return this.stock;
    }

}
