package nl.averageflow.springwarehouse.domain.category.dto;

public record AddCategoriesRequest(Iterable<AddCategoriesRequestItem> items) {
}