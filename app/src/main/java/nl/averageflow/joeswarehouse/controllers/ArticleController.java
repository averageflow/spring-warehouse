package nl.averageflow.joeswarehouse.controllers;

import nl.averageflow.joeswarehouse.models.Article;
import nl.averageflow.joeswarehouse.requests.AddArticlesRequest;
import nl.averageflow.joeswarehouse.responses.ArticleResponse;
import nl.averageflow.joeswarehouse.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin
public final class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/api/articles")
    public ArticleResponse getArticles() {
        return this.articleService.getArticles();
    }

    @GetMapping("/api/articles/{uid}")
    public Optional<Article> getArticle(@PathVariable UUID uid) {
        return this.articleService.getArticleByUid(uid);
    }

    @PostMapping("/api/articles")
    public void addArticles(@RequestBody AddArticlesRequest request) {
        this.articleService.addArticles(request.getInventory());
    }

    @DeleteMapping("/api/articles/{uid}")
    public void deleteArticle(@PathVariable UUID uid) {
        this.articleService.deleteArticleByUid(uid);
    }
}
