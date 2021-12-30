package nl.averageflow.springwarehouse.domain.category.dto;

import javax.validation.constraints.NotEmpty;

public record AddCategoriesRequest(@NotEmpty Iterable<AddCategoriesRequestItem> items) {
}