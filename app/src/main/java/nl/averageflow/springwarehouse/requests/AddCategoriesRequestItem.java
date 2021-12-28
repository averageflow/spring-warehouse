package nl.averageflow.springwarehouse.requests;

public final class AddCategoriesRequestItem {
    private final String name;
    private final String description;

    public AddCategoriesRequestItem(final String name, final String description) {
        this.name = name;
        this.description = description;
    }


    public String getName() {
        return this.name;
    }


    public String getDescription() {
        return description;
    }
}
