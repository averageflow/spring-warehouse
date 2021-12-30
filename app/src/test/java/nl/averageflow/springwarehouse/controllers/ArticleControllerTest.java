package nl.averageflow.springwarehouse.controllers;

import nl.averageflow.springwarehouse.domain.article.ArticleController;
import nl.averageflow.springwarehouse.domain.article.ArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ArticleControllerTest {

    @Autowired
    private ArticleController controller;

    @MockBean
    private ArticleService articleService;

    @Test
    public void contextLoads() {
        assertThat(controller).isNotNull();
    }
}
