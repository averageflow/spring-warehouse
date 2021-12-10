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
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public final class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ArticleStocksRepository articleStocksRepository;

    @Autowired
    private ProductArticleRepository productArticleRepository;

    public static Iterable<Article> convertAddArticleRequestToMappedList(Iterable<AddArticlesRequestItem> rawItems) {
        return StreamSupport.stream(rawItems.spliterator(), false).map(ArticleService::articleRequestItemConverter).collect(Collectors.toList());
    }

    private static Article articleRequestItemConverter(AddArticlesRequestItem rawItem) {
        return new Article(rawItem);
    }

    public Iterable<ArticleStock> convertAddArticleStockRequestToMappedList(Iterable<AddArticlesRequestItem> rawItems) {
        return StreamSupport.stream(rawItems.spliterator(), false)
                .map(this::articleRequestItemStockConverter)
                .collect(Collectors.toList());
    }

    private ArticleStock articleRequestItemStockConverter(AddArticlesRequestItem rawItem) {
        return new ArticleStock(this.articleRepository.findByItemId(rawItem.getItemId()).get(), rawItem.getStock());
    }

    public Page<Article> getArticles(Pageable pageable) {
        return this.articleRepository.findAll(pageable);
    }

    public Optional<Article> getArticleByUid(UUID uid) {
        return this.articleRepository.findByUid(uid);
    }

    public Iterable<Article> addArticles(Iterable<AddArticlesRequestItem> rawItems) {
        Iterable<Article> convertedArticles = convertAddArticleRequestToMappedList(rawItems);
        this.articleRepository.saveAll(convertedArticles);

        Iterable<ArticleStock> convertedArticleStock = convertAddArticleStockRequestToMappedList(rawItems);
        this.articleStocksRepository.saveAll(convertedArticleStock);

        return convertedArticles;
    }

    public Article editArticle(UUID uid, EditArticleRequest request) {
        Optional<Article> wantedArticleSearchResult = this.articleRepository.findByUid(uid);

        if (wantedArticleSearchResult.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "could not find item with wanted UUID");
        }

        Article itemToUpdate = wantedArticleSearchResult.get();

        itemToUpdate.setName(request.getName());

        return this.articleRepository.save(itemToUpdate);
    }

    public void deleteArticleByUid(UUID uid) {
        this.productArticleRepository.deleteByArticleUid(uid);
        this.articleStocksRepository.deleteByArticleUid(uid);
        this.articleRepository.deleteByUid(uid);
    }
}
