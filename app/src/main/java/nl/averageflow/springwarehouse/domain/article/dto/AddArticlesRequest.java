package nl.averageflow.springwarehouse.domain.article.dto;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public record AddArticlesRequest(@NotEmpty @NotNull Iterable<AddArticlesRequestItem> inventory) {
}