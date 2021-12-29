package nl.averageflow.springwarehouse.requests;

public record AddCategoriesRequest(Iterable<AddCategoriesRequestItem> items) {
}