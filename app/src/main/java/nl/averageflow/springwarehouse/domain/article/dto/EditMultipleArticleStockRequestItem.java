package nl.averageflow.springwarehouse.domain.article.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public record EditMultipleArticleStockRequestItem(@NotNull @NotBlank UUID itemUid, @NotNull @NotBlank Long stock) {
}
