package nl.averageflow.springwarehouse.domain.product.dto;

import javax.validation.constraints.NotEmpty;

public record AddProductRequest(@NotEmpty Iterable<AddProductsRequestItem> products) {

}