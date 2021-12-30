package nl.averageflow.springwarehouse.domain.article.dto;


public record AddArticlesRequest(Iterable<AddArticlesRequestItem> inventory) {
}