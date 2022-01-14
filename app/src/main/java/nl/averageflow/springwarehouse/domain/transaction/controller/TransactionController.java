package nl.averageflow.springwarehouse.domain.transaction.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import nl.averageflow.springwarehouse.domain.category.dto.CategoryResponseItem;
import nl.averageflow.springwarehouse.domain.transaction.dto.TransactionResponseItem;
import nl.averageflow.springwarehouse.domain.transaction.service.TransactionService;
import nl.averageflow.springwarehouse.domain.transaction.service.TransactionServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Tag(name = "Transaction", description = "Transaction API")
public final class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(final TransactionServiceImpl transactionService) {
        this.transactionService = transactionService;
    }


    @GetMapping("/api/transactions")
    @Operation(summary = "Returns the requested page of transactions",
            description = "Returns the requested page of transactions specifying the page number")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful Response",
                    content = @Content(schema = @Schema(implementation = CategoryResponseItem.class))),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    public Page<TransactionResponseItem> getTransactions(final Pageable pageable) {
        return this.transactionService.getTransactions(pageable);
    }


    @GetMapping("/api/transactions/{uid}")
    @Operation(summary = "Returns a transaction",
            description = "Returns a single transaction by its uuid")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful Response",
                    content = @Content(schema = @Schema(implementation = CategoryResponseItem.class))),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    public TransactionResponseItem getTransaction(@PathVariable final UUID uid) {
        return this.transactionService.getTransactionByUid(uid);
    }
}
