package nl.averageflow.springwarehouse.requests;

public record AddProductRequest(Iterable<AddProductsRequestItem> products) {

}