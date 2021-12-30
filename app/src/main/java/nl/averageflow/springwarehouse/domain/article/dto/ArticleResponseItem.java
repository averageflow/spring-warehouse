package nl.averageflow.springwarehouse.domain.article.dto;

import java.sql.Timestamp;
import java.util.UUID;

public record ArticleResponseItem(
        UUID uid,
        String name,
        long stock,
        Timestamp createdAt,
        Timestamp updatedAt) {
}
