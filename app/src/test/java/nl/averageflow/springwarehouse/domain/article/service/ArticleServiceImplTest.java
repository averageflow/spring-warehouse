package nl.averageflow.springwarehouse.domain.article.service;

import com.github.javafaker.Faker;
import nl.averageflow.springwarehouse.domain.article.dto.AddArticlesRequestItem;
import nl.averageflow.springwarehouse.domain.article.dto.ArticleResponseItem;
import nl.averageflow.springwarehouse.domain.article.model.Article;
import nl.averageflow.springwarehouse.domain.article.repository.ArticleRepository;
import nl.averageflow.springwarehouse.domain.article.repository.ArticleStocksRepository;
import nl.averageflow.springwarehouse.domain.product.repository.ProductArticleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ArticleServiceImplTest {

    @Mock
    private ArticleRepository articleRepository;

    @Mock
    private ArticleStocksRepository articleStocksRepository;

    @Mock
    private ProductArticleRepository productArticleRepository;

    private ArticleService articleService;

    private Faker faker;

    @BeforeEach
    public void setUp(){
        this.faker = new Faker();

        this.articleService = new ArticleServiceImpl(
                this.articleRepository,
                this.articleStocksRepository,
                this.productArticleRepository
        );
    }

    @Test
    public void testGetArticleByUid(){
        final Article article = new Article(this.faker.commerce().productName());
        final UUID uid = UUID.randomUUID();

        final ArticleResponseItem expectedResult = new ArticleResponseItem(
                article.getUid(),
                article.getName(),
                article.getStock(),
                article.getCreatedAt(),
                article.getUpdatedAt()
        );

        when(this.articleRepository.findByUid(uid)).thenReturn(Optional.of(article));

        final ArticleResponseItem sut = this.articleService.getArticleByUid(uid);

        assertEquals(sut, expectedResult);
        verify(this.articleRepository, times(1)).findByUid(uid);
    }

    @Test
    public void testGetArticles(){
        final Article article = new Article(this.faker.commerce().productName());
        final Pageable pageable = mock(Pageable.class);

        final ArticleResponseItem formattedItem = new ArticleResponseItem(
                article.getUid(),
                article.getName(),
                article.getStock(),
                article.getCreatedAt(),
                article.getUpdatedAt()
        );

        final Page<Article> articlePage = new PageImpl<>(List.of(article));

        when(this.articleRepository.findAll(pageable)).thenReturn(articlePage);

        final Page<ArticleResponseItem> sut = this.articleService.getArticles(pageable);

        assertEquals(sut, new PageImpl<>(List.of(formattedItem)));
        verify(this.articleRepository, times(1)).findAll(any(Pageable.class));
    }

    @Test
    public void testAddArticlesEmptyIterable() {
        this.articleService.addArticles(Collections.emptyList());

        verify(this.articleRepository, times(0)).save(any());
    }

    @Test
    public void testAddArticles(){
        final AddArticlesRequestItem addArticlesRequestItem = new AddArticlesRequestItem(
                this.faker.commerce().productName(),
                this.faker.number().numberBetween(1, 100)
        );

        final Collection<AddArticlesRequestItem> itemsToAdd = List.of(
                addArticlesRequestItem,
                addArticlesRequestItem,
                addArticlesRequestItem
        );

        this.articleService.addArticles(itemsToAdd);

        verify(this.articleRepository, times(itemsToAdd.size())).save(any(Article.class));
    }

    @Test
    public void testDeleteArticleByUid(){
        final UUID uid = UUID.randomUUID();

        this.articleService.deleteArticleByUid(uid);

        verify(this.productArticleRepository, times(1)).deleteByArticleUid(uid);
        verify(this.articleStocksRepository, times(1)).deleteByArticleUid(uid);
        verify(this.articleRepository, times(1)).deleteByUid(uid);
    }

}
