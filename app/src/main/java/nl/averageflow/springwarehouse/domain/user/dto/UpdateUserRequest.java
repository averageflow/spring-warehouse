package nl.averageflow.springwarehouse.domain.user.dto;

import javax.validation.constraints.NotBlank;

public record UpdateUserRequest(@NotBlank String role,
                                @NotBlank String email) {
}
