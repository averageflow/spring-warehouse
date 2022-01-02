package nl.averageflow.springwarehouse.domain.product;

import nl.averageflow.springwarehouse.domain.product.dto.AddProductRequest;
import nl.averageflow.springwarehouse.domain.product.dto.EditProductRequest;
import nl.averageflow.springwarehouse.domain.product.dto.ProductResponseItem;
import nl.averageflow.springwarehouse.domain.product.dto.SellProductsRequest;
import nl.averageflow.springwarehouse.domain.transaction.TransactionResponseItem;
import nl.averageflow.springwarehouse.domain.transaction.TransactionService;
import nl.averageflow.springwarehouse.domain.transaction.TransactionServiceContract;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
public final class ProductController {

    private final ProductServiceContract productService;

    private final TransactionServiceContract transactionService;

    public ProductController(final ProductService productService, final TransactionService transactionService) {
        this.productService = productService;
        this.transactionService = transactionService;
    }

    @GetMapping("/api/products")
    public Page<ProductResponseItem> getProducts(final Pageable pageable) {
        return this.productService.getProducts(pageable);
    }

    @GetMapping("/api/products/{uid}")
    public ProductResponseItem getProduct(@PathVariable final UUID uid) {
        return this.productService.getProductByUid(uid);
    }

    @PostMapping("/api/products")
    public void addProducts(@RequestBody @Valid final AddProductRequest request) {
        this.productService.addProducts(request.products());
    }

    @DeleteMapping("/api/products/{uid}")
    public void deleteProduct(@PathVariable final UUID uid) {
        this.productService.deleteProductByUid(uid);
    }

    @PatchMapping("/api/products/{uid}")
    public ProductResponseItem editProduct(@PathVariable final UUID uid, @RequestBody final EditProductRequest request) {
        return this.productService.editProduct(uid, request);
    }

    @PatchMapping("/api/products/sell")
    public TransactionResponseItem sellProducts(final Authentication authentication, @RequestBody @Valid final SellProductsRequest request) {
        this.productService.sellProducts(request);
        return this.transactionService.createTransaction(request, authentication);
    }
}
