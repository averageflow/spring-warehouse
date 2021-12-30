package nl.averageflow.springwarehouse.domain.article;

import nl.averageflow.springwarehouse.domain.article.dto.AddArticlesRequestItem;
import nl.averageflow.springwarehouse.domain.article.dto.ArticleResponseItem;
import nl.averageflow.springwarehouse.domain.article.dto.EditArticleRequest;
import nl.averageflow.springwarehouse.domain.article.model.Article;
import nl.averageflow.springwarehouse.domain.article.model.ArticleStock;
import nl.averageflow.springwarehouse.domain.article.repository.ArticleRepository;
import nl.averageflow.springwarehouse.domain.article.repository.ArticleStocksRepository;
import nl.averageflow.springwarehouse.domain.product.repository.ProductArticleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@Service
public class ArticleService implements ArticleServiceContract {

    private final ArticleRepository articleRepository;

    private final ArticleStocksRepository articleStocksRepository;

    private final ProductArticleRepository productArticleRepository;

    public ArticleService(final ArticleRepository articleRepository, final ArticleStocksRepository articleStocksRepository, final ProductArticleRepository productArticleRepository) {
        this.articleRepository = articleRepository;
        this.articleStocksRepository = articleStocksRepository;
        this.productArticleRepository = productArticleRepository;
    }

    public Page<ArticleResponseItem> getArticles(final Pageable pageable) {
        final Page<Article> page = this.articleRepository.findAll(pageable);

        return page.map(article -> new ArticleResponseItem(
                article.getUid(),
                article.getName(),
                article.getStock(),
                article.getCreatedAt(),
                article.getUpdatedAt()
        ));
    }

    public ArticleResponseItem getArticleByUid(final UUID uid) {
        final Article article = this.articleRepository.findByUid(uid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return new ArticleResponseItem(
                article.getUid(),
                article.getName(),
                article.getStock(),
                article.getCreatedAt(),
                article.getUpdatedAt()
        );
    }


    public void addArticles(final Iterable<AddArticlesRequestItem> rawItems) {
        rawItems.forEach(this::addSingleArticle);
    }


    public ArticleResponseItem editArticle(final UUID uid, final EditArticleRequest request) {
        final Optional<Article> searchResult = this.articleRepository.findByUid(uid);

        if (searchResult.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "could not find item with wanted UUID");
        }

        final Article itemToUpdate = searchResult.get();

        itemToUpdate.setName(request.name());

        final Article updatedItem = this.articleRepository.save(itemToUpdate);

        return new ArticleResponseItem(
                updatedItem.getUid(),
                updatedItem.getName(),
                updatedItem.getStock(),
                updatedItem.getCreatedAt(),
                updatedItem.getUpdatedAt()
        );
    }


    public void deleteArticleByUid(final UUID uid) {
        this.productArticleRepository.deleteByArticleUid(uid);
        this.articleStocksRepository.deleteByArticleUid(uid);
        this.articleRepository.deleteByUid(uid);
    }

    /**
     * Add a single article into the system
     *
     * @param rawItem the article
     */
    private void addSingleArticle(final AddArticlesRequestItem rawItem) {
        final Article article = new Article(rawItem);
        final ArticleStock articleStock = new ArticleStock(article, rawItem.stock());

        this.articleRepository.save(article);
        this.articleStocksRepository.save(articleStock);
    }
}
