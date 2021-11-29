package nl.averageflow.joeswarehouse.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import nl.averageflow.joeswarehouse.models.Product;
import nl.averageflow.joeswarehouse.models.ProductService;
import nl.averageflow.joeswarehouse.requests.AddProductRequest;
import nl.averageflow.joeswarehouse.responses.ProductResponse;

@RestController
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/api/products")
    public ProductResponse getProducts() {
        return this.productService.getProducts();
    }

    @GetMapping("/api/products/{id}")
    public Optional<Product> getProduct(@PathVariable Long id) {
        return this.productService.getProductByID(id);
    }

    @PostMapping("/api/products")
    public void addProducts(@RequestBody AddProductRequest request) {
        System.out.println(request);
    }

    @DeleteMapping("/api/products/{id}")
    public void deleteProduct(@PathVariable Long id) {
        this.productService.deleteProductByID(id);
    }
}
