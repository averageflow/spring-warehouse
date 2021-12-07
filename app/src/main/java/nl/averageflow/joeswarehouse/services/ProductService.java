package nl.averageflow.joeswarehouse.services;

import nl.averageflow.joeswarehouse.models.ArticleAmountInProduct;
import nl.averageflow.joeswarehouse.models.Product;
import nl.averageflow.joeswarehouse.repositories.ArticleRepository;
import nl.averageflow.joeswarehouse.repositories.ProductArticleRepository;
import nl.averageflow.joeswarehouse.repositories.ProductRepository;
import nl.averageflow.joeswarehouse.requests.AddProductRequest;
import nl.averageflow.joeswarehouse.requests.AddProductsRequestItem;
import nl.averageflow.joeswarehouse.responses.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public final class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ProductArticleRepository productArticleRepository;

    private static List<Product> convertAddProductRequestToProductList(List<AddProductsRequestItem> rawItems) {
        return rawItems.stream().map(Product::new).collect(Collectors.toList());
    }

    private List<List<ArticleAmountInProduct>> convertAddProductArticleRequestToList(List<AddProductsRequestItem> rawItems) {
        return rawItems.stream().map(
                item -> item.getContainArticles().stream().map(
                        articleItem -> new ArticleAmountInProduct(
                                this.productRepository.findByItemId(item.getItemId()).get(),
                                this.articleRepository.findByItemId(articleItem.getItemId()).get(),
                                articleItem.getAmountOf()
                        )
                ).collect(Collectors.toList())
        ).collect(Collectors.toList());
    }

    public ProductResponse getProducts() {
        return new ProductResponse(this.productRepository.findAll());
    }

    public Optional<Product> getProductByUid(UUID uid) {
        return this.productRepository.findByUid(uid);
    }

    public void deleteProductByUid(UUID uid) {
        this.productRepository.deleteByUid(uid);
    }

    public void addProducts(AddProductRequest request) {
        List<Product> convertedProducts = convertAddProductRequestToProductList(request.getProducts());
        this.productRepository.saveAll(convertedProducts);

        List<List<ArticleAmountInProduct>> convertedArticleProductRelations = convertAddProductArticleRequestToList(request.getProducts());
        convertedArticleProductRelations.forEach(item -> this.productArticleRepository.saveAll(item));
    }
}
