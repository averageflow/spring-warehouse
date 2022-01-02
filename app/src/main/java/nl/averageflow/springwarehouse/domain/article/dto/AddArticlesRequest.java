package nl.averageflow.springwarehouse.domain.article.dto;


import javax.validation.constraints.NotEmpty;
import java.util.Collection;

public record AddArticlesRequest(@NotEmpty Collection<AddArticlesRequestItem> inventory) {
}
