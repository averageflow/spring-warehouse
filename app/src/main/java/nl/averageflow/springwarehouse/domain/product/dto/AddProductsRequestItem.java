package nl.averageflow.springwarehouse.domain.product.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.UUID;

public record AddProductsRequestItem(
        @NotBlank String name,
        @NotNull Double price,
        @NotNull UUID categoryUid,
        @NotEmpty Collection<AddProductsRequestArticleItem> containArticles,
        @NotEmpty Collection<String> imageURLs) {
}
