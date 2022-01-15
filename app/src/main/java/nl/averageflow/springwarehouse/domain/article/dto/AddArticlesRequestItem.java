package nl.averageflow.springwarehouse.domain.article.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public record AddArticlesRequestItem(@NotBlank String name, @Min(0) long stock) {
}
