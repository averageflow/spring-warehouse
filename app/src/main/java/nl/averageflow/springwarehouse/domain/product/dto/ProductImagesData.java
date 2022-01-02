package nl.averageflow.springwarehouse.domain.product.dto;

import javax.validation.constraints.NotEmpty;
import java.util.Collection;

public record ProductImagesData(@NotEmpty Collection<String> urls) {

}
