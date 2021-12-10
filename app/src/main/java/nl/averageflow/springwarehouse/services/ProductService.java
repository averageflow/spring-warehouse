package nl.averageflow.springwarehouse.services;

import nl.averageflow.springwarehouse.models.ArticleAmountInProduct;
import nl.averageflow.springwarehouse.models.Product;
import nl.averageflow.springwarehouse.repositories.ArticleRepository;
import nl.averageflow.springwarehouse.repositories.ProductArticleRepository;
import nl.averageflow.springwarehouse.repositories.ProductRepository;
import nl.averageflow.springwarehouse.requests.*;
import org.springframework.beans.factory.annotation.Autowired;
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
public final class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ProductArticleRepository productArticleRepository;

    private static Iterable<Product> convertAddProductRequestToProductList(Iterable<AddProductsRequestItem> rawItems) {
        return StreamSupport.stream(rawItems.spliterator(), false).map(Product::new).collect(Collectors.toList());
    }

    private Iterable<Iterable<ArticleAmountInProduct>> convertAddProductArticleRequestToList(Iterable<AddProductsRequestItem> rawItems) {
        return StreamSupport.stream(rawItems.spliterator(), false)
                .map(item -> StreamSupport.stream(item.getContainArticles().spliterator(), false)
                        .map(articleItem -> new ArticleAmountInProduct(
                                        this.productRepository.findByItemId(item.getItemId()).get(),
                                        this.articleRepository.findByItemId(articleItem.getItemId()).get(),
                                        articleItem.getAmountOf()
                                )
                        ).collect(Collectors.toList())
                ).collect(Collectors.toList());
    }

    public Page<Product> getProducts(Pageable pageable) {
        return this.productRepository.findAll(pageable);
    }

    public Optional<Product> getProductByUid(UUID uid) {
        return this.productRepository.findByUid(uid);
    }

    public void deleteProductByUid(UUID uid) {
        this.productRepository.deleteByUid(uid);
    }

    public Iterable<Product> addProducts(AddProductRequest request) {
        Iterable<Product> convertedProducts = convertAddProductRequestToProductList(request.getProducts());
        this.productRepository.saveAll(convertedProducts);

        Iterable<Iterable<ArticleAmountInProduct>> convertedArticleProductRelations = convertAddProductArticleRequestToList(request.getProducts());
        convertedArticleProductRelations.forEach(item -> this.productArticleRepository.saveAll(item));

        return convertedProducts;
    }

    public void sellProducts(SellProductsRequest request) {
        Iterable<UUID> wantedUUIDs = StreamSupport.stream(request.getWantedItemsForSale().spliterator(), false)
                .map(SellProductsRequestItem::getItemUid)
                .collect(Collectors.toList());

        HashMap<UUID, Long> wantedAmountsPerProduct = new HashMap<>();
        StreamSupport.stream(request.getWantedItemsForSale().spliterator(), false).forEach(item -> {
            wantedAmountsPerProduct.put(item.getItemUid(), item.getAmountOf());
        });

        Iterable<Product> wantedProducts = this.productRepository.findAllById(wantedUUIDs);

        StreamSupport.stream(wantedProducts.spliterator(), false).forEach(wantedItemForSale -> {
                this.reserveItemStock( wantedAmountsPerProduct.get(wantedItemForSale.getUid()), wantedItemForSale);
            }
        );
    }

    private void reserveItemStock(long wantedProductAmount, Product product){
        long productStock = product.getProductStock();
        boolean isValidAmount = productStock >= wantedProductAmount &&
                productStock - wantedProductAmount >= 0 &&
                productStock > 0;

        if (!isValidAmount) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "not enough stock to perform sale");
        }


        product.getArticles().forEach(articleAmountInProduct -> {
            long wantedArticleAmount = articleAmountInProduct.getAmountOf() * wantedProductAmount;
            articleAmountInProduct.getArticle().performStockBooking(wantedArticleAmount);
        });

        this.productRepository.save(product);
    }

    public Product editProduct(UUID uid, EditProductRequest request) {
        Optional<Product> wantedProductSearchResult = this.productRepository.findByUid(uid);

        if (wantedProductSearchResult.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "could not find item with wanted UUID");
        }

        Product itemToUpdate = wantedProductSearchResult.get();

        itemToUpdate.setName(request.getName());
        itemToUpdate.setPrice(request.getPrice());

        return this.productRepository.save(itemToUpdate);
    }
}
