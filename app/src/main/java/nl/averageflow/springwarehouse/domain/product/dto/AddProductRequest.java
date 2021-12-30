package nl.averageflow.springwarehouse.domain.product.dto;

public record AddProductRequest(Iterable<AddProductsRequestItem> products) {

}