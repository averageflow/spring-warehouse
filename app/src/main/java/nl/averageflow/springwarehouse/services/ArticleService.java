package nl.averageflow.springwarehouse.services;

import nl.averageflow.springwarehouse.models.Article;
import nl.averageflow.springwarehouse.models.ArticleStock;
import nl.averageflow.springwarehouse.repositories.ArticleRepository;
import nl.averageflow.springwarehouse.repositories.ArticleStocksRepository;
import nl.averageflow.springwarehouse.repositories.ProductArticleRepository;
import nl.averageflow.springwarehouse.requests.AddArticlesRequestItem;
import nl.averageflow.springwarehouse.requests.EditArticleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@Service
public final class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ArticleStocksRepository articleStocksRepository;

    @Autowired
    private ProductArticleRepository productArticleRepository;

    public Page<Article> getArticles(final Pageable pageable) {
        return this.articleRepository.findAll(pageable);
    }

    public Optional<Article> getArticleByUid(final UUID uid) {
        return this.articleRepository.findByUid(uid);
    }

    public void addArticles(final Iterable<AddArticlesRequestItem> rawItems) {
        rawItems.forEach(rawItem -> {
            final Article article = new Article(rawItem);
            final ArticleStock articleStock = new ArticleStock(article, rawItem.getStock());

            this.articleRepository.save(article);
            this.articleStocksRepository.save(articleStock);
        });
    }

    public Article editArticle(final UUID uid, final EditArticleRequest request) {
        final Optional<Article> wantedArticleSearchResult = this.articleRepository.findByUid(uid);

        if (wantedArticleSearchResult.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "could not find item with wanted UUID");
        }

        final Article itemToUpdate = wantedArticleSearchResult.get();

        itemToUpdate.setName(request.getName());

        return this.articleRepository.save(itemToUpdate);
    }

    public void deleteArticleByUid(final UUID uid) {
        this.productArticleRepository.deleteByArticleUid(uid);
        this.articleStocksRepository.deleteByArticleUid(uid);
        this.articleRepository.deleteByUid(uid);
    }
}
