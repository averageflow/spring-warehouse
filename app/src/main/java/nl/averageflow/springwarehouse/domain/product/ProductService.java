package nl.averageflow.springwarehouse.domain.product;

import nl.averageflow.springwarehouse.domain.article.model.Article;
import nl.averageflow.springwarehouse.domain.article.repository.ArticleRepository;
import nl.averageflow.springwarehouse.domain.category.Category;
import nl.averageflow.springwarehouse.domain.category.CategoryRepository;
import nl.averageflow.springwarehouse.domain.category.dto.CategoryResponseItem;
import nl.averageflow.springwarehouse.domain.product.dto.*;
import nl.averageflow.springwarehouse.domain.product.model.ArticleAmountInProduct;
import nl.averageflow.springwarehouse.domain.product.model.Product;
import nl.averageflow.springwarehouse.domain.product.repository.ProductArticleRepository;
import nl.averageflow.springwarehouse.domain.product.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProductService implements ProductServiceContract {

    private final ProductRepository productRepository;

    private final ArticleRepository articleRepository;

    private final CategoryRepository categoryRepository;

    private final ProductArticleRepository productArticleRepository;

    public ProductService(final ProductRepository productRepository, final ArticleRepository articleRepository, final CategoryRepository categoryRepository, final ProductArticleRepository productArticleRepository) {
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
        final Optional<Product> searchResult = this.productRepository.findByUid(uid);
        if (searchResult.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        final Product product = searchResult.get();

        return new ProductResponseItem(
                product.getUid(),
                product.getName(),
                product.getImageURLs(),
                product.getPrice(),
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
    }

    public void deleteProductByUid(final UUID uid) {
        this.productRepository.deleteByUid(uid);
    }

    public void addProducts(final Iterable<AddProductsRequestItem> rawItems) {
        rawItems.forEach(rawItem -> {
            final Optional<Category> category = this.categoryRepository.findByUid(rawItem.categoryUid());
            if (category.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "could not find wanted category");
            }

            final Product product = new Product(rawItem, category.get());

            final Iterable<ArticleAmountInProduct> productArticles = StreamSupport.stream(rawItem.containArticles().spliterator(), false)
                    .map(articleItem -> {
                        final Optional<Article> article = this.articleRepository.findByUid(articleItem.uid());
                        if (article.isEmpty()) {
                            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "could not find wanted article");
                        }

                        return new ArticleAmountInProduct(
                                product,
                                article.get(),
                                articleItem.amountOf()
                        );
                    }).toList();

            this.productRepository.save(product);
            this.productArticleRepository.saveAll(productArticles);
        });
    }

    public void sellProducts(final SellProductsRequest request) {
        final Iterable<UUID> wantedUUIDs = StreamSupport.stream(request.wantedItemsForSale().spliterator(), false)
                .map(SellProductsRequestItem::itemUid)
                .collect(Collectors.toList());

        final HashMap<UUID, Long> wantedAmountsPerProduct = new HashMap<>();
        StreamSupport.stream(request.wantedItemsForSale().spliterator(), false)
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
        final Optional<Product> searchResult = this.productRepository.findByUid(uid);
        final Optional<Category> category = this.categoryRepository.findByUid(request.categoryUid());

        if (searchResult.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "could not find product with wanted UUID");
        }

        if (category.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "could not find category with wanted UUID");
        }

        final Product itemToUpdate = searchResult.get();


        itemToUpdate.setName(request.name());
        itemToUpdate.setPrice(request.price());
        itemToUpdate.setImageURLs(request.imageURLs());
        itemToUpdate.setCategory(category.get());

        final Product updatedItem = this.productRepository.save(itemToUpdate);

        return new ProductResponseItem(
                updatedItem.getUid(),
                updatedItem.getName(),
                updatedItem.getImageURLs(),
                updatedItem.getPrice(),
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
