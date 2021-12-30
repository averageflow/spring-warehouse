package nl.averageflow.springwarehouse.authentication.dto;

public record RegisterRequest(String name, String email, String password) {
}
