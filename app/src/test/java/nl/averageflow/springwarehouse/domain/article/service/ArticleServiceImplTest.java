package nl.averageflow.springwarehouse.domain.article.service;

import com.github.javafaker.Faker;
import nl.averageflow.springwarehouse.domain.article.dto.AddArticlesRequestItem;
import nl.averageflow.springwarehouse.domain.article.dto.ArticleResponseItem;
import nl.averageflow.springwarehouse.domain.article.dto.EditArticleRequest;
import nl.averageflow.springwarehouse.domain.article.dto.EditMultipleArticleStockRequestItem;
import nl.averageflow.springwarehouse.domain.article.model.Article;
import nl.averageflow.springwarehouse.domain.article.repository.ArticleRepository;
import nl.averageflow.springwarehouse.domain.article.repository.ArticleStocksRepository;
import nl.averageflow.springwarehouse.domain.product.repository.ProductArticleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
    public void setUp() {
        this.faker = new Faker();

        this.articleService = new ArticleServiceImpl(
                this.articleRepository,
                this.articleStocksRepository,
                this.productArticleRepository
        );
    }

    @Test
    public void testGetArticleByUid() {
        final var article = new Article(this.faker.commerce().productName());
        final var uid = UUID.randomUUID();

        final var expectedResult = new ArticleResponseItem(
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
    public void testGetArticles() {
        final var article = new Article(this.faker.commerce().productName());
        final var pageable = mock(Pageable.class);

        final var formattedItem = new ArticleResponseItem(
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
    public void testAddArticlesEmptyCollection() {
        this.articleService.addArticles(Collections.emptyList());

        verify(this.articleRepository, times(0)).save(any());
    }

    @Test
    public void testAddArticles() {
        final var addArticlesRequestItem = new AddArticlesRequestItem(
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
    public void testDeleteArticleByUid() {
        final var uid = UUID.randomUUID();

        this.articleService.deleteArticleByUid(uid);

        verify(this.productArticleRepository, times(1)).deleteByArticleUid(uid);
        verify(this.articleStocksRepository, times(1)).deleteByArticleUid(uid);
        verify(this.articleRepository, times(1)).deleteByUid(uid);
    }

    @Test
    public void testEditArticle() {
        final var uid = UUID.randomUUID();
        final var editArticleRequest = new EditArticleRequest(
                this.faker.commerce().productName()
        );
        final var fakeArticle = new Article(this.faker.commerce().productName());

        when(this.articleRepository.findByUid(uid)).thenReturn(Optional.of(fakeArticle));
        when(this.articleRepository.save(any(Article.class))).thenReturn(fakeArticle);

        this.articleService.editArticle(uid, editArticleRequest);

        verify(this.articleRepository, times(1)).save(any(Article.class));
    }

    @Test
    public void testEditMultipleArticleStock() {
        final var pageable = mock(Pageable.class);
        final var fakeUpdateItem = new EditMultipleArticleStockRequestItem(
                UUID.randomUUID(),
                this.faker.number().numberBetween(1L, 100L)
        );
        final var fakeArticle = new Article(this.faker.commerce().productName());
        final Collection<EditMultipleArticleStockRequestItem> editMultipleArticleStockRequestItems = List.of(
                fakeUpdateItem,
                fakeUpdateItem,
                fakeUpdateItem
        );

        when(this.articleRepository.findByUid(any(UUID.class))).thenReturn(Optional.of(fakeArticle));
        when(this.articleRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(List.of(
                fakeArticle,
                fakeArticle,
                fakeArticle
        )));

        this.articleService.editMultipleArticleStock(pageable, editMultipleArticleStockRequestItems);

        verify(this.articleRepository, times(editMultipleArticleStockRequestItems.size())).findByUid(any(UUID.class));
    }
}
