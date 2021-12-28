package nl.averageflow.springwarehouse.requests;

public final class AddArticlesRequestItem {
    private final String name;
    private final long stock;

    public AddArticlesRequestItem(final String name, final long stock) {
        this.name = name;
        this.stock = stock;
    }


    public String getName() {
        return this.name;
    }


    public long getStock() {
        return this.stock;
    }

}
