package nl.averageflow.springwarehouse.product.dto;

public record AddProductRequest(Iterable<AddProductsRequestItem> products) {

}