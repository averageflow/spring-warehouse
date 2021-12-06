package nl.averageflow.joeswarehouse.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import nl.averageflow.joeswarehouse.models.Article;
import nl.averageflow.joeswarehouse.requests.AddArticlesRequest;
import nl.averageflow.joeswarehouse.responses.ArticleResponse;
import nl.averageflow.joeswarehouse.services.ArticleService;

@RestController
@CrossOrigin
public final class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/api/articles")
    public ArticleResponse getArticles() {
        return this.articleService.getArticles();
    }

    @GetMapping("/api/articles/{id}")
    public Optional<Article> getArticle(@PathVariable Long id) {
        return this.articleService.getArticleByID(id);
    }

    @PostMapping("/api/articles")
    public void addArticles(@RequestBody AddArticlesRequest request) {
        this.articleService.addArticles(request.getInventory());
    }

    @DeleteMapping("/api/articles/{id}")
    public void deleteArticle(@PathVariable Long id) {
        this.articleService.deleteArticleByID(id);
    }
}
