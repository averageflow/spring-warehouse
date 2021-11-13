package nl.averageflow.joeswarehouse.articles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nl.averageflow.joeswarehouse.articlestocks.ArticleStocksRepository;

@Service
final class ArticleService {

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
        return rawItems.stream().map(rawItem -> articleRequestItemConverter(rawItem)).collect(Collectors.toList());
    }

    private Article articleRequestItemConverter(AddArticlesRequestItem rawItem) {
        Article article = new Article(rawItem);
        this.articleStocksRepository.save(article.getStockEntity());
        return article;
    }

    public void addArticles(List<AddArticlesRequestItem> rawItems) {
        this.articleRepository.saveAll(this.convertAddArticleRequestToMappedList(rawItems));
    }
}
