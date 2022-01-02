package nl.averageflow.springwarehouse.domain.article;

import nl.averageflow.springwarehouse.domain.article.dto.AddArticlesRequest;
import nl.averageflow.springwarehouse.domain.article.dto.ArticleResponseItem;
import nl.averageflow.springwarehouse.domain.article.dto.EditArticleRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@RestController
public final class ArticleController {
    private final ArticleServiceContract articleService;

    public ArticleController(final ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/api/articles")
    public Page<ArticleResponseItem> getArticles(@NotNull final Pageable pageable) {
        return this.articleService.getArticles(pageable);
    }

    @GetMapping("/api/articles/{uid}")
    public ArticleResponseItem getArticle(@PathVariable @NotNull final UUID uid) {
        return this.articleService.getArticleByUid(uid);
    }

    @PostMapping("/api/articles")
    public void addArticles(@RequestBody @Valid final AddArticlesRequest request) {
        this.articleService.addArticles(request.inventory());
    }

    @PatchMapping("/api/articles/{uid}")
    public ArticleResponseItem editArticle(@PathVariable @NotNull final UUID uid, @RequestBody @Valid final EditArticleRequest request) {
        return this.articleService.editArticle(uid, request);
    }

    @DeleteMapping("/api/articles/{uid}")
    public void deleteArticle(@PathVariable @NotNull final UUID uid) {
        this.articleService.deleteArticleByUid(uid);
    }
}
