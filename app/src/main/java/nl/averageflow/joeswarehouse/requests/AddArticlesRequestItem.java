package nl.averageflow.joeswarehouse.requests;

public final class AddArticlesRequestItem {
    private String itemId;
    private String name;
    private String stock;

    protected AddArticlesRequestItem() {
    }

    public String getArt_id() {
        return this.itemId;
    }

    public void setArt_id(String art_id) {
        this.itemId = art_id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStock() {
        return this.stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }
}
