package nl.averageflow.springwarehouse.domain.category.dto;

import javax.validation.constraints.NotBlank;

public record AddCategoriesRequestItem(@NotBlank String name,
                                       @NotBlank String description) {
}
