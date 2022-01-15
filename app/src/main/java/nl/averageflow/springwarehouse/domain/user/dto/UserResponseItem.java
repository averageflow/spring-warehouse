package nl.averageflow.springwarehouse.domain.user.dto;

import java.sql.Timestamp;
import java.util.UUID;

public record UserResponseItem(
        UUID uid,
        String itemName,
        String email,
        String role,
        Timestamp createdAt,
        Timestamp updatedAt) {

}
