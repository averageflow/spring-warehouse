package nl.averageflow.joeswarehouse.services;

import nl.averageflow.joeswarehouse.models.Article;
import nl.averageflow.joeswarehouse.models.ArticleStock;
import nl.averageflow.joeswarehouse.repositories.ArticleRepository;
import nl.averageflow.joeswarehouse.repositories.ArticleStocksRepository;
import nl.averageflow.joeswarehouse.requests.AddArticlesRequestItem;
import nl.averageflow.joeswarehouse.responses.ArticleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public final class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ArticleStocksRepository articleStocksRepository;

    public ArticleResponse getArticles() {
        return new ArticleResponse(this.articleRepository.findAll());
    }

    public Optional<Article> getArticleByID(Long id) {
        return this.articleRepository.findById(id);
    }

    public List<Article> convertAddArticleRequestToMappedList(List<AddArticlesRequestItem> rawItems) {
        return rawItems.stream().map(this::articleRequestItemConverter).collect(Collectors.toList());
    }

    public List<ArticleStock> convertAddArticleStockRequestToMappedList(List<AddArticlesRequestItem> rawItems) {
        return rawItems.stream().map(this::articleRequestItemStockConverter).collect(Collectors.toList());
    }

    private Article articleRequestItemConverter(AddArticlesRequestItem rawItem) {
        return new Article(rawItem);
    }

    private ArticleStock articleRequestItemStockConverter(AddArticlesRequestItem rawItem) {
        return new ArticleStock(rawItem);
    }

    public void addArticles(List<AddArticlesRequestItem> rawItems) {
        List<Article> convertedArticles = this.convertAddArticleRequestToMappedList(rawItems);
        this.articleRepository.saveAll(convertedArticles);

        List<ArticleStock> convertedArticleStock = this.convertAddArticleStockRequestToMappedList(rawItems);
        this.articleStocksRepository.saveAll(convertedArticleStock);

    }

    public void deleteArticleByID(Long id) {
        this.articleRepository.deleteById(id);
    }
}
