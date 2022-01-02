package nl.averageflow.springwarehouse.domain.category;

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
public class CategoryController {

    private final CategoryServiceContract categoryService;

    public CategoryController(final CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/api/categories")
    public Page<CategoryResponseItem> getCategories(@NotNull final Pageable pageable) {
        return this.categoryService.getCategories(pageable);
    }

    @GetMapping("/api/categories/{uid}")
    public CategoryResponseItem getCategory(@PathVariable @NotNull final UUID uid) {
        return this.categoryService.getCategoryByUid(uid);
    }

    @PostMapping("/api/categories")
    public void addCategories(@RequestBody @Valid final AddCategoriesRequest request) {
        this.categoryService.addCategories(request.items());
    }

    @PatchMapping("/api/categories/{uid}")
    public CategoryResponseItem editCategory(@PathVariable @NotNull final UUID uid, @RequestBody @Valid final EditCategoryRequest request) {
        return this.categoryService.editCategory(uid, request);
    }

    @DeleteMapping("/api/categories/{uid}")
    public void deleteCategory(@PathVariable @NotNull final UUID uid) {
        this.categoryService.deleteCategoryByUid(uid);
    }
}
