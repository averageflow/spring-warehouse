package nl.averageflow.springwarehouse.domain.category.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.UUID;

public record CategoryResponseItem(
        @NotNull UUID uid,
        @NotBlank String name,
        @NotBlank String description,
        Timestamp createdAt,
        Timestamp updatedAt) {
}
