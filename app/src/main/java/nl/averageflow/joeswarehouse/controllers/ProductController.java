package nl.averageflow.joeswarehouse.controllers;

import nl.averageflow.joeswarehouse.models.Product;
import nl.averageflow.joeswarehouse.requests.AddProductRequest;
import nl.averageflow.joeswarehouse.responses.ProductResponse;
import nl.averageflow.joeswarehouse.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
public final class ProductController {

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
        this.productService.addProducts(request);
    }

    @DeleteMapping("/api/products/{id}")
    public void deleteProduct(@PathVariable Long id) {
        this.productService.deleteProductByID(id);
    }
}
