package nl.averageflow.springwarehouse.requests;


public record AddArticlesRequest(Iterable<AddArticlesRequestItem> inventory) {
}