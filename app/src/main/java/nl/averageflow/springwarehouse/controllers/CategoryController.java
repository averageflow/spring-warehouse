package nl.averageflow.springwarehouse.controllers;

import nl.averageflow.springwarehouse.models.Article;
import nl.averageflow.springwarehouse.requests.AddCategoriesRequest;
import nl.averageflow.springwarehouse.requests.EditArticleRequest;
import nl.averageflow.springwarehouse.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin
public final class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/api/categories")
    public Page<Article> getCategories(final Pageable pageable) {
        return this.categoryService.getCategories(pageable);
    }

    @GetMapping("/api/categories/{uid}")
    public Optional<Article> getCategory(@PathVariable final UUID uid) {
        return this.categoryService.getCategoryByUid(uid);
    }

    @PostMapping("/api/categories")
    public void addCategories(@RequestBody final AddCategoriesRequest request) {
        this.categoryService.addCategories(request.getItems());
    }

    @PatchMapping("/api/categories/{uid}")
    public Article editCategory(@PathVariable final UUID uid, @RequestBody final EditArticleRequest request) {
        return this.categoryService.editCategory(uid, request);
    }

    @DeleteMapping("/api/categories/{uid}")
    public void deleteCategory(@PathVariable final UUID uid) {
        this.categoryService.deleteArticleByUid(uid);
    }
}
