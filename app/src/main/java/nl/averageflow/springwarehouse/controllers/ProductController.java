package nl.averageflow.springwarehouse.controllers;

import nl.averageflow.springwarehouse.models.Product;
import nl.averageflow.springwarehouse.requests.AddProductRequest;
import nl.averageflow.springwarehouse.requests.SellProductsRequest;
import nl.averageflow.springwarehouse.responses.ProductResponse;
import nl.averageflow.springwarehouse.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin
public final class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/api/products")
    public Page<Product> getProducts(Pageable pageable) {
        return this.productService.getProducts(pageable);
    }

    @GetMapping("/api/products/{uid}")
    public Optional<Product> getProduct(@PathVariable UUID uid) {
        return this.productService.getProductByUid(uid);
    }

    @PostMapping("/api/products")
    public void addProducts(@RequestBody AddProductRequest request) {
        this.productService.addProducts(request);
    }

    @DeleteMapping("/api/products/{uid}")
    public void deleteProduct(@PathVariable UUID uid) {
        this.productService.deleteProductByUid(uid);
    }

    @PatchMapping("/api/products")
    public void sellProducts(@RequestBody SellProductsRequest request) {
        this.productService.sellProducts(request);
    }
}
