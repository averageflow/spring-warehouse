package nl.averageflow.joeswarehouse.controllers;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import nl.averageflow.joeswarehouse.products.Product;
import nl.averageflow.joeswarehouse.products.ProductRepository;

@RestController
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @GetMapping("/api/products")
    public List<Product> getProducts() {
        return this.repository.findAll();
    }

    @GetMapping("/api/products/{id}")
    public Optional<Product> getProduct(@PathVariable Long id) {
        return this.repository.findById(id);
    }
}
