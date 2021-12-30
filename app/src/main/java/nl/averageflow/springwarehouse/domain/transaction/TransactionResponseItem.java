package nl.averageflow.springwarehouse.domain.transaction;

import java.sql.Timestamp;
import java.util.UUID;

public record TransactionResponseItem(UUID uid, Timestamp createdAt) {
}
