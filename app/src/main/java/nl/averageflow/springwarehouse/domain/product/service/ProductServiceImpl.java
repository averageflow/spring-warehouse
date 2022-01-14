package nl.averageflow.springwarehouse.domain.product.service;

import nl.averageflow.springwarehouse.domain.article.model.Article;
import nl.averageflow.springwarehouse.domain.article.repository.ArticleRepository;
import nl.averageflow.springwarehouse.domain.category.dto.CategoryResponseItem;
import nl.averageflow.springwarehouse.domain.category.model.Category;
import nl.averageflow.springwarehouse.domain.category.repository.CategoryRepository;
import nl.averageflow.springwarehouse.domain.product.dto.AddProductsRequestItem;
import nl.averageflow.springwarehouse.domain.product.dto.EditProductRequest;
import nl.averageflow.springwarehouse.domain.product.dto.ProductResponseItem;
import nl.averageflow.springwarehouse.domain.product.dto.SellProductsRequest;
import nl.averageflow.springwarehouse.domain.product.dto.SellProductsRequestItem;
import nl.averageflow.springwarehouse.domain.product.model.ArticleAmountInProduct;
import nl.averageflow.springwarehouse.domain.product.model.Product;
import nl.averageflow.springwarehouse.domain.product.repository.ProductArticleRepository;
import nl.averageflow.springwarehouse.domain.product.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;
import java.util.stream.StreamSupport;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ArticleRepository articleRepository;

    private final CategoryRepository categoryRepository;

    private final ProductArticleRepository productArticleRepository;

    public ProductServiceImpl(final ProductRepository productRepository,
                              final ArticleRepository articleRepository,
                              final CategoryRepository categoryRepository,
                              final ProductArticleRepository productArticleRepository) {
        this.productRepository = productRepository;
        this.articleRepository = articleRepository;
        this.categoryRepository = categoryRepository;
        this.productArticleRepository = productArticleRepository;
    }


    public Page<ProductResponseItem> getProducts(final Pageable pageable) {
        final Page<Product> page = this.productRepository.findAll(pageable);

        return page.map(product -> new ProductResponseItem(
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
        ));
    }

    public ProductResponseItem getProductByUid(final UUID uid) {
        final Product product = this.productRepository.findByUid(uid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        final Category productCategory = product.getCategory();

        return new ProductResponseItem(
                product.getUid(),
                product.getName(),
                product.getImageURLs(),
                product.getPrice(),
                product.getProductStock(),
                new CategoryResponseItem(
                        productCategory.getUid(),
                        productCategory.getName(),
                        productCategory.getDescription(),
                        productCategory.getCreatedAt(),
                        productCategory.getUpdatedAt()
                ),
                product.getCreatedAt(),
                product.getUpdatedAt(),
                product.getArticles()
        );
    }

    public void deleteProductByUid(final UUID uid) {
        this.productRepository.deleteByUid(uid);
    }

    public void addProducts(final Collection<AddProductsRequestItem> rawItems) {
        rawItems.forEach(rawItem -> {
            final Category category = this.categoryRepository.findByUid(rawItem.categoryUid())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "could not find wanted category"));

            final var product = new Product(
                    rawItem.name(),
                    rawItem.price(),
                    rawItem.imageURLs(),
                    category
            );

            final Collection<ArticleAmountInProduct> productArticles = rawItem.containArticles().stream()
                    .map(articleItem -> {
                        final Article article = this.articleRepository.findByUid(articleItem.uid())
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "could not find wanted article"));

                        return new ArticleAmountInProduct(
                                product,
                                article,
                                articleItem.amountOf()
                        );
                    }).toList();

            this.productRepository.save(product);
            this.productArticleRepository.saveAll(productArticles);
        });
    }

    public void sellProducts(final SellProductsRequest request) {
        final Collection<UUID> wantedUUIDs = request.wantedItemsForSale().stream()
                .map(SellProductsRequestItem::itemUid)
                .toList();

        final HashMap<UUID, Long> wantedAmountsPerProduct = new HashMap<>();
        request.wantedItemsForSale()
                .forEach(item -> wantedAmountsPerProduct.put(item.itemUid(), item.amountOf()));

        final Iterable<Product> wantedProducts = this.productRepository.findAllById(wantedUUIDs);

        StreamSupport.stream(wantedProducts.spliterator(), false)
                .forEach(wantedItemForSale -> this.reserveItemStock(wantedAmountsPerProduct.get(wantedItemForSale.getUid()), wantedItemForSale)
                );
    }

    public void reserveItemStock(final long wantedProductAmount, final Product product) {
        final long productStock = product.getProductStock();
        final boolean isValidAmount = productStock >= wantedProductAmount &&
                productStock - wantedProductAmount >= 0 &&
                productStock > 0;

        if (!isValidAmount) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "not enough stock to perform sale");
        }


        product.getArticles().forEach(articleAmountInProduct -> {
            final long wantedArticleAmount = articleAmountInProduct.getAmountOf() * wantedProductAmount;
            articleAmountInProduct.getArticle().performStockBooking(wantedArticleAmount);
        });

        this.productRepository.save(product);
    }

    public ProductResponseItem editProduct(final UUID uid, final EditProductRequest request) {
        final Product product = this.productRepository.findByUid(uid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "could not find product with wanted UUID"));
        final Category category = this.categoryRepository.findByUid(request.categoryUid())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "could not find category with wanted UUID"));

        product.setName(request.name());
        product.setPrice(request.price());
        product.setImageURLs(request.imageURLs());
        product.setCategory(category);

        final Product updatedItem = this.productRepository.save(product);

        return new ProductResponseItem(
                updatedItem.getUid(),
                updatedItem.getName(),
                updatedItem.getImageURLs(),
                updatedItem.getPrice(),
                updatedItem.getProductStock(),
                new CategoryResponseItem(
                        updatedItem.getCategory().getUid(),
                        updatedItem.getCategory().getName(),
                        updatedItem.getCategory().getDescription(),
                        updatedItem.getCategory().getCreatedAt(),
                        updatedItem.getCategory().getUpdatedAt()
                ),
                updatedItem.getCreatedAt(),
                updatedItem.getUpdatedAt(),
                updatedItem.getArticles()
        );
    }
}
