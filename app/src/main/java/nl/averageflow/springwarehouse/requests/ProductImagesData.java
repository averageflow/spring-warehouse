package nl.averageflow.springwarehouse.requests;

public class ProductImagesData {
    private Iterable<String> urls;

    public void setUrls(Iterable<String> urls) {
        this.urls = urls;
    }

    public Iterable<String> getUrls() {
        return urls;
    }

    public ProductImagesData(){}

    public ProductImagesData(Iterable<String> urls){
        this.urls = urls;
    }
}
