package nl.averageflow.springwarehouse.controllers;

import nl.averageflow.springwarehouse.category.CategoryController;
import nl.averageflow.springwarehouse.category.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CategoryControllerTest {

    @Autowired
    private CategoryController controller;

    @MockBean
    private CategoryService categoryService;

    @Test
    public void contextLoads() {
        assertThat(controller).isNotNull();
    }
}
