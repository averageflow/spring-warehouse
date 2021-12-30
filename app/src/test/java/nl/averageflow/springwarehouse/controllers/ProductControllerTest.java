package nl.averageflow.springwarehouse.controllers;

import nl.averageflow.springwarehouse.product.ProductController;
import nl.averageflow.springwarehouse.product.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ProductControllerTest {

    @Autowired
    private ProductController controller;

    @MockBean
    private ProductService productService;

    @Test
    public void contextLoads() {
        assertThat(controller).isNotNull();
    }
}
