package nl.averageflow.springwarehouse.infrastructure.errorhandling;

public record Violation(String fieldName, String message) {
}
