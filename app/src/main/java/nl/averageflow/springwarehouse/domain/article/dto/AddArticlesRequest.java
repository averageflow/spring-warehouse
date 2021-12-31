package nl.averageflow.springwarehouse.domain.article.dto;


import java.util.Collection;

import javax.validation.constraints.NotEmpty;

public record AddArticlesRequest(@NotEmpty Collection<AddArticlesRequestItem> inventory) {
}
