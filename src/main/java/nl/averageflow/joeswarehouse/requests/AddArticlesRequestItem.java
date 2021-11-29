package nl.averageflow.joeswarehouse.requests;

public class AddArticlesRequestItem {
    private String art_id;
    private String name;
    private String stock;

    public String getArt_id() {
        return this.art_id;
    }

    public void setArt_id(String art_id) {
        this.art_id = art_id;
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

    protected AddArticlesRequestItem() {
    }
}
