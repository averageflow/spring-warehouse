package nl.averageflow.springwarehouse.domain.product.service;

import com.github.javafaker.Faker;
import nl.averageflow.springwarehouse.domain.article.repository.ArticleRepository;
import nl.averageflow.springwarehouse.domain.category.dto.CategoryResponseItem;
import nl.averageflow.springwarehouse.domain.category.model.Category;
import nl.averageflow.springwarehouse.domain.category.repository.CategoryRepository;
import nl.averageflow.springwarehouse.domain.product.dto.ProductResponseItem;
import nl.averageflow.springwarehouse.domain.product.model.Product;
import nl.averageflow.springwarehouse.domain.product.repository.ProductArticleRepository;
import nl.averageflow.springwarehouse.domain.product.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ArticleRepository articleRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ProductArticleRepository productArticleRepository;

    private ProductService productService;

    private Faker faker;

    @BeforeEach
    public void setUp() {
        this.productService = new ProductServiceImpl(
                this.productRepository,
                this.articleRepository,
                this.categoryRepository,
                this.productArticleRepository
        );

        this.faker = new Faker();
    }

    @Test
    public void testGetProducts() {
        final var pageable = mock(Pageable.class);
        final var fakeCategory = new Category(
                this.faker.commerce().department(),
                this.faker.commerce().department()
        );

        final var product = new Product(
                this.faker.commerce().productName(),
                Double.parseDouble(this.faker.commerce().price(1, 1000)),
                Collections.emptyList(),
                fakeCategory
        );

        final var formattedItem = new ProductResponseItem(
                product.getUid(),
                product.getName(),
                product.getImageURLs(),
                product.getPrice(),
                product.getProductStock(),
                new CategoryResponseItem(
                        product.getCategory().getUid(),
                        product.getCategory().getName(),
                        product.getCategory().getDescription(),
                        product.getCategory().getCreatedAt(),
                        product.getCategory().getUpdatedAt()
                ),
                product.getCreatedAt(),
                product.getUpdatedAt(),
                product.getArticles()
        );

        final Page<Product> page = new PageImpl<>(List.of(product));

        when(this.productRepository.findAll(pageable)).thenReturn(page);

        final Page<ProductResponseItem> sut = this.productService.getProducts(pageable);

        assertEquals(sut, new PageImpl<>(List.of(formattedItem)));
        verify(this.productRepository, times(1)).findAll(pageable);
    }

    @Test
    public void testGetProductByUid() {
        final var uid = UUID.randomUUID();
        final var fakeCategory = new Category(
                this.faker.commerce().department(),
                this.faker.commerce().department()
        );

        final var product = new Product(
                this.faker.commerce().productName(),
                Double.parseDouble(this.faker.commerce().price(1, 1000)),
                Collections.emptyList(),
                fakeCategory
        );

        final var formattedItem = new ProductResponseItem(
                product.getUid(),
                product.getName(),
                product.getImageURLs(),
                product.getPrice(),
                product.getProductStock(),
                new CategoryResponseItem(
                        product.getCategory().getUid(),
                        product.getCategory().getName(),
                        product.getCategory().getDescription(),
                        product.getCategory().getCreatedAt(),
                        product.getCategory().getUpdatedAt()
                ),
                product.getCreatedAt(),
                product.getUpdatedAt(),
                product.getArticles()
        );

        when(this.productRepository.findByUid(uid)).thenReturn(Optional.of(product));

        final ProductResponseItem sut = this.productService.getProductByUid(uid);

        assertEquals(sut, formattedItem);
        verify(this.productRepository, times(1)).findByUid(uid);
    }

    @Test
    public void testDeleteProductByUid() {
        final var uid = UUID.randomUUID();

        this.productService.deleteProductByUid(uid);

        verify(this.productRepository, times(1)).deleteByUid(uid);
    }
}
