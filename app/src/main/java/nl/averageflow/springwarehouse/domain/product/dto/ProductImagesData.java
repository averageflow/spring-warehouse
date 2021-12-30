package nl.averageflow.springwarehouse.domain.product.dto;

import javax.validation.constraints.NotEmpty;

public record ProductImagesData(@NotEmpty Iterable<String> urls) {

}
