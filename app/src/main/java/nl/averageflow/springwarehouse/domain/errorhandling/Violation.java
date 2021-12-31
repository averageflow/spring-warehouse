package nl.averageflow.springwarehouse.domain.errorhandling;

public record Violation(String fieldName, String message) {
}
