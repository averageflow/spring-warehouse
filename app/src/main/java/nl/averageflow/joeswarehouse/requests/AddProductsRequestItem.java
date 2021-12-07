package nl.averageflow.joeswarehouse.requests;

public final class AddProductsRequestItem {
    private Long itemId;
    private String name;
    private Double price;
    private Iterable<AddProductsRequestArticleItem> containArticles;


    protected AddProductsRequestItem() {

    }

    public Long getItemId() {
        return this.itemId;
    }

    public Double getPrice() {
        return this.price;
    }

    public String getName() {
        return this.name;
    }

    public Iterable<AddProductsRequestArticleItem> getContainArticles() {
        return containArticles;
    }
}
