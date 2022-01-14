package nl.averageflow.springwarehouse.domain.article.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import nl.averageflow.springwarehouse.domain.article.dto.AddArticlesRequest;
import nl.averageflow.springwarehouse.domain.article.dto.ArticleResponseItem;
import nl.averageflow.springwarehouse.domain.article.dto.EditArticleRequest;
import nl.averageflow.springwarehouse.domain.article.dto.EditMultipleArticleStockRequest;
import nl.averageflow.springwarehouse.domain.article.service.ArticleService;
import nl.averageflow.springwarehouse.domain.article.service.ArticleServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@RestController
@Tag(name = "Article", description = "Article API")
public final class ArticleController {

    private final ArticleService articleService;

    public ArticleController(final ArticleServiceImpl articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/api/articles")
    @Operation(summary = "Returns the requested page of articles",
            description = "Returns a page of article specifying the page number and the number of elements to show")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful Response",
                    content = @Content(schema = @Schema(implementation = Page.class))),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    public Page<ArticleResponseItem> getArticles(@NotNull final Pageable pageable) {
        return this.articleService.getArticles(pageable);
    }

    @GetMapping("/api/articles/{uid}")
    @Operation(summary = "Returns the requested article",
            description = "Returns the requested article specifying its uuid")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful Response",
                    content = @Content(schema = @Schema(implementation = ArticleResponseItem.class))),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    public ArticleResponseItem getArticle(@PathVariable @NotNull final UUID uid) {
        return this.articleService.getArticleByUid(uid);
    }

    @PostMapping("/api/articles")
    @Operation(summary = "Add some articles into the system",
            description = "Add a collection of articles into the system. The Collection should not be empty.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful Response"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    public void addArticles(@RequestBody @Valid final AddArticlesRequest request) {
        this.articleService.addArticles(request.inventory());
    }

    @PatchMapping("/api/articles/{uid}")
    @Operation(summary = "Edit an article",
            description = "The service permits editing a single article.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful Response",
                    content = @Content(schema = @Schema(implementation = ArticleResponseItem.class))),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    public ArticleResponseItem editArticle(@PathVariable @NotNull final UUID uid,
                                           @RequestBody @Valid final EditArticleRequest request) {
        return this.articleService.editArticle(uid, request);
    }

    @PatchMapping("/api/articles/stock")
    @Operation(summary = "Edit multiple article's stock",
            description = "The service permits editing multiple article's stock.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful Response",
                    content = @Content(schema = @Schema(implementation = ArticleResponseItem.class))),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    public Page<ArticleResponseItem> editMultipleArticleStock(@NotNull final Pageable pageable,
                                                              @RequestBody @Valid final EditMultipleArticleStockRequest request) {
        return this.articleService.editMultipleArticleStock(pageable, request.articles());
    }

    @DeleteMapping("/api/articles/{uid}")
    @Operation(summary = "Edit an article",
            description = "The service permits editing a single article.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful Response",
                    content = @Content(schema = @Schema(implementation = ArticleResponseItem.class))),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    public void deleteArticle(@PathVariable @NotNull final UUID uid) {
        this.articleService.deleteArticleByUid(uid);
    }
}
