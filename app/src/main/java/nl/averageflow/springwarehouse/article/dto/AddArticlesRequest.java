package nl.averageflow.springwarehouse.article.dto;


public record AddArticlesRequest(Iterable<AddArticlesRequestItem> inventory) {
}