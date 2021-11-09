package nl.averageflow.joeswarehouse.articles;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
final class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public ArticleResponse getArticles() {
        return new ArticleResponse(this.articleRepository.findAll());
    }

    public Optional<Article> getArticleByID(Long id) {
        return this.articleRepository.findById(id);
    }

    public void deleteArticleByID(Long id) {
        this.articleRepository.deleteById(id);
    }
}
