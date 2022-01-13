package nl.averageflow.springwarehouse.domain.article.service;

import nl.averageflow.springwarehouse.domain.article.dto.AddArticlesRequestItem;
import nl.averageflow.springwarehouse.domain.article.dto.ArticleResponseItem;
import nl.averageflow.springwarehouse.domain.article.dto.EditArticleRequest;
import nl.averageflow.springwarehouse.domain.article.dto.EditMultipleArticleStockRequestItem;
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

import java.util.Collection;
import java.util.UUID;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    private final ArticleStocksRepository articleStocksRepository;

    private final ProductArticleRepository productArticleRepository;

    public ArticleServiceImpl(final ArticleRepository articleRepository,
                              final ArticleStocksRepository articleStocksRepository,
                              final ProductArticleRepository productArticleRepository) {
        this.articleRepository = articleRepository;
        this.articleStocksRepository = articleStocksRepository;
        this.productArticleRepository = productArticleRepository;
    }

    @Override
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

    @Override
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

    @Override
    public void addArticles(final Collection<AddArticlesRequestItem> rawItems) {
        rawItems.forEach(this::addSingleArticle);
    }

    @Override
    public ArticleResponseItem editArticle(final UUID uid, final EditArticleRequest request) {
        final Article itemToUpdate = this.articleRepository.findByUid(uid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

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

    @Override
    public Page<ArticleResponseItem> editMultipleArticleStock(final Pageable pageable,
                                                              final Collection<EditMultipleArticleStockRequestItem> articles) {
        final Page<Article> page = this.articleRepository.findAll(pageable);

        articles.forEach(article -> {
            final Article itemToUpdate = this.articleRepository.findByUid(article.itemUid())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            itemToUpdate.setStock(new ArticleStock(article.stock()));
            this.articleRepository.save(itemToUpdate);
        });

        return page.map(articleStock -> new ArticleResponseItem(
                articleStock.getUid(),
                articleStock.getName(),
                articleStock.getStock(),
                articleStock.getCreatedAt(),
                articleStock.getUpdatedAt()
        ));
    }

    @Override
    public void deleteArticleByUid(final UUID uid) {
        this.productArticleRepository.deleteByArticleUid(uid);
        this.articleStocksRepository.deleteByArticleUid(uid);
        this.articleRepository.deleteByUid(uid);
    }

    /**
     * Add a single article into the system.
     *
     * @param rawItem the article
     */
    private void addSingleArticle(final AddArticlesRequestItem rawItem) {
        final var article = new Article(rawItem.name());
        final var articleStock = new ArticleStock(article, rawItem.stock());

        this.articleRepository.save(article);
        this.articleStocksRepository.save(articleStock);
    }
}
