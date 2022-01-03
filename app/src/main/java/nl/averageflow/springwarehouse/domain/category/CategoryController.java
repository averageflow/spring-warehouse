package nl.averageflow.springwarehouse.domain.category;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import nl.averageflow.springwarehouse.domain.category.dto.AddCategoriesRequest;
import nl.averageflow.springwarehouse.domain.category.dto.CategoryResponseItem;
import nl.averageflow.springwarehouse.domain.category.dto.EditCategoryRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@RestController
@Tag(name = "Category", description = "Category API")
public class CategoryController {

    private final CategoryServiceContract categoryService;

    public CategoryController(final CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/api/categories")
    @Operation(summary = "Returns the requested page of categories",
            description = "Returns the requested page of categories specifying the page number")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Response",
                    content = @Content(schema = @Schema(implementation = Page.class))),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    public Page<CategoryResponseItem> getCategories(@NotNull final Pageable pageable) {
        return this.categoryService.getCategories(pageable);
    }

    @GetMapping("/api/categories/{uid}")
    @Operation(summary = "Returns a single category",
            description = "Returns a single category specifying its uuid")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Response",
                    content = @Content(schema = @Schema(implementation = CategoryResponseItem.class))),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    public CategoryResponseItem getCategory(@PathVariable @NotNull final UUID uid) {
        return this.categoryService.getCategoryByUid(uid);
    }

    @PostMapping("/api/categories")
    @Operation(summary = "Add categories",
            description = "Add a collection of categories into the system. The collection should not be empty.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Response"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    public void addCategories(@RequestBody @Valid final AddCategoriesRequest request) {
        this.categoryService.addCategories(request.items());
    }

    @PatchMapping("/api/categories/{uid}")
    @Operation(summary = "Edit a category",
            description = "The service permits editing a single category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Response",
                    content = @Content(schema = @Schema(implementation = CategoryResponseItem.class))),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    public CategoryResponseItem editCategory(@PathVariable @NotNull final UUID uid, @RequestBody @Valid final EditCategoryRequest request) {
        return this.categoryService.editCategory(uid, request);
    }

    @DeleteMapping("/api/categories/{uid}")
    @Operation(summary = "Delete a category",
            description = "The service permits deleting a single category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Response"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    public void deleteCategory(@PathVariable @NotNull final UUID uid) {
        this.categoryService.deleteCategoryByUid(uid);
    }
}
