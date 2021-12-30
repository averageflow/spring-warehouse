package nl.averageflow.springwarehouse.domain.category.dto;

import java.sql.Timestamp;
import java.util.UUID;

public record CategoryResponseItem(
        UUID uid,
        String name,
        String description,
        Timestamp createdAt,
        Timestamp updatedAt) {
}
