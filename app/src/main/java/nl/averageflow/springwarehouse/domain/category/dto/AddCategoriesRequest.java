package nl.averageflow.springwarehouse.domain.category.dto;

import javax.validation.constraints.NotEmpty;
import java.util.Collection;

public record AddCategoriesRequest(@NotEmpty Collection<AddCategoriesRequestItem> items) {
}