package nl.averageflow.springwarehouse.domain.product.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import nl.averageflow.springwarehouse.domain.product.dto.AddProductRequest;
import nl.averageflow.springwarehouse.domain.product.dto.EditProductRequest;
import nl.averageflow.springwarehouse.domain.product.dto.ProductResponseItem;
import nl.averageflow.springwarehouse.domain.product.dto.SellProductsRequest;
import nl.averageflow.springwarehouse.domain.product.service.ProductService;
import nl.averageflow.springwarehouse.domain.product.service.ProductServiceImpl;
import nl.averageflow.springwarehouse.domain.transaction.dto.TransactionResponseItem;
import nl.averageflow.springwarehouse.domain.transaction.service.TransactionService;
import nl.averageflow.springwarehouse.domain.transaction.service.TransactionServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@Tag(name = "Product", description = "Product API")
public final class ProductController {

    private final ProductService productService;

    private final TransactionService transactionService;

    public ProductController(final ProductServiceImpl productService, final TransactionServiceImpl transactionService) {
        this.productService = productService;
        this.transactionService = transactionService;
    }

    @GetMapping("/api/products")
    @Operation(summary = "Returns the requested page of products",
            description = "Returns the requested page of products specifying the page number")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful Response",
                    content = @Content(schema = @Schema(implementation = Page.class))),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    public Page<ProductResponseItem> getProducts(final Pageable pageable) {
        return this.productService.getProducts(pageable);
    }

    @GetMapping("/api/products/{uid}")
    @Operation(summary = "Returns a single product",
            description = "Returns a single product specifying its uuid")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful Response",
                    content = @Content(schema = @Schema(implementation = ProductResponseItem.class))),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    public ProductResponseItem getProduct(@PathVariable final UUID uid) {
        return this.productService.getProductByUid(uid);
    }

    @PostMapping("/api/products")
    @Operation(summary = "Add products",
            description = "Add a collection of products into the system. The collection should not be empty.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful Response"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    public void addProducts(@RequestBody @Valid final AddProductRequest request) {
        this.productService.addProducts(request.products());
    }

    @DeleteMapping("/api/products/{uid}")
    @Operation(summary = "Delete a product",
            description = "The service permits deleting a single product by its uuid")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful Response"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    public void deleteProduct(@PathVariable final UUID uid) {
        this.productService.deleteProductByUid(uid);
    }

    @PatchMapping("/api/products/{uid}")
    @Operation(summary = "Edit a product",
            description = "The service permits editing a single product")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful Response",
                    content = @Content(schema = @Schema(implementation = ProductResponseItem.class))),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    public ProductResponseItem editProduct(@PathVariable final UUID uid, @RequestBody final EditProductRequest request) {
        return this.productService.editProduct(uid, request);
    }

    @PatchMapping("/api/products/sell")
    @Operation(summary = "Sell products",
            description = "The service permits selling a collection of products")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful Response",
                    content = @Content(schema = @Schema(implementation = TransactionResponseItem.class))),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    public TransactionResponseItem sellProducts(final Authentication authentication, @RequestBody @Valid final SellProductsRequest request) {
        this.productService.sellProducts(request);
        return this.transactionService.createTransaction(request, authentication);
    }
}
