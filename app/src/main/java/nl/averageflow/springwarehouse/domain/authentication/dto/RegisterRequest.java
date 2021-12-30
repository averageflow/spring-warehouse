package nl.averageflow.springwarehouse.domain.authentication.dto;

public record RegisterRequest(String name, String email, String password) {
}
