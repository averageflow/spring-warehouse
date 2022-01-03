package nl.averageflow.springwarehouse.domain.product;

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
@Tag(name = "Product", description = "Product API")
public final class ProductController {

    private final ProductServiceContract productService;

    private final TransactionServiceContract transactionService;

    public ProductController(final ProductService productService, final TransactionService transactionService) {
        this.productService = productService;
        this.transactionService = transactionService;
    }

    @GetMapping("/api/products")
    @Operation(summary = "Returns the requested page of products",
            description = "Returns the requested page of products specifying the page number")
    @ApiResponses(value = {
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
    @ApiResponses(value = {
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
    @ApiResponses(value = {
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
    @ApiResponses(value = {
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
    @ApiResponses(value = {
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
    @ApiResponses(value = {
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
