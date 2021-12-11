package nl.averageflow.springwarehouse.requests;

public final class AddProductsRequestItem {
    private long itemId;
    private String name;
    private Double price;
    private Iterable<AddProductsRequestArticleItem> containArticles;
    private Iterable<String> imageURLs;


    protected AddProductsRequestItem() {

    }

    public long getItemId() {
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

    public Iterable<String> getImageURLs() {
        return imageURLs;
    }
}
