package nl.averageflow.springwarehouse.category.dto;

public record AddCategoriesRequest(Iterable<AddCategoriesRequestItem> items) {
}