package nl.averageflow.springwarehouse.domain.article.service;

import nl.averageflow.springwarehouse.domain.article.dto.AddArticlesRequestItem;
import nl.averageflow.springwarehouse.domain.article.dto.ArticleResponseItem;
import nl.averageflow.springwarehouse.domain.article.dto.EditArticleRequest;
import nl.averageflow.springwarehouse.domain.article.dto.EditMultipleArticleStockRequestItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.UUID;

public interface ArticleService {
    /**
     * Get a paginated list of articles in the system.
     *
     * @param pageable
     * @return a paginated list of articles
     */
    Page<ArticleResponseItem> getArticles(final Pageable pageable);

    /**
     * Get an article by its UUID.
     *
     * @param uid is the UUID of the article.
     * @return the article item.
     */
    ArticleResponseItem getArticleByUid(final UUID uid);

    /**
     * Add a collection of articles in the system.
     *
     * @param rawItems are the request items to be added.
     */
    void addArticles(final Collection<AddArticlesRequestItem> rawItems);

    /**
     * Edit an article's properties by its UUID.
     *
     * @param uid     is the UUID of the article.
     * @param request is the request body containing the update payload.
     * @return the updated article.
     */
    ArticleResponseItem editArticle(final UUID uid, final EditArticleRequest request);

    /**
     * Edit the stock of several articles.
     *
     * @param pageable
     * @param articles
     * @return the updated articles.
     */
    Page<ArticleResponseItem> editMultipleArticleStock(final Pageable pageable, Collection<EditMultipleArticleStockRequestItem> articles);

    /**
     * Delete an article by its UUID.
     *
     * @param uid the UUID of the article.
     */
    void deleteArticleByUid(final UUID uid);
}
