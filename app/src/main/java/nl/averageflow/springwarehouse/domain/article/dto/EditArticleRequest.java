package nl.averageflow.springwarehouse.domain.article.dto;

import javax.validation.constraints.NotBlank;

public record EditArticleRequest(@NotBlank String name) {

}
