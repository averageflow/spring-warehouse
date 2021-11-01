package nl.averageflow.joeswarehouse.products;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
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
}
