package nl.averageflow.springwarehouse.domain.article.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record AddArticlesRequestItem(@NotBlank @NotNull String name, @Min(1) long stock) {
}
