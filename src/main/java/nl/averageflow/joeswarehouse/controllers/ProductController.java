package nl.averageflow.joeswarehouse.controllers;

import java.time.Instant;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import nl.averageflow.joeswarehouse.products.Product;

@RestController
public class ProductController {

    @GetMapping("/api/products")
    public Product getProducts() {
        return new Product("name", 999.9, Instant.now().getEpochSecond());
    }

    @GetMapping("/api/products/{id}")
    public Product getProduct(@PathVariable Long id) {
        return new Product("name", 999.9, Instant.now().getEpochSecond());
    }
}
