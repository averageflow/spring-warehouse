package nl.averageflow.springwarehouse.domain.article;

import nl.averageflow.springwarehouse.domain.article.dto.AddArticlesRequestItem;
import nl.averageflow.springwarehouse.domain.article.dto.ArticleResponseItem;
import nl.averageflow.springwarehouse.domain.article.dto.EditArticleRequest;
import nl.averageflow.springwarehouse.domain.article.dto.EditMultipleArticleStockRequestItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.UUID;

public interface ArticleServiceContract {
    Page<ArticleResponseItem> getArticles(final Pageable pageable);

    ArticleResponseItem getArticleByUid(final UUID uid);

    void addArticles(final Collection<AddArticlesRequestItem> rawItems);

    ArticleResponseItem editArticle(final UUID uid, final EditArticleRequest request);

    Page<ArticleResponseItem> editMultipleArticleStock(final Pageable pageable,Collection<EditMultipleArticleStockRequestItem> articles);

    void deleteArticleByUid(final UUID uid);
}
