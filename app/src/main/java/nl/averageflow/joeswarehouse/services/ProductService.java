package nl.averageflow.joeswarehouse.services;

import nl.averageflow.joeswarehouse.models.Product;
import nl.averageflow.joeswarehouse.repositories.ProductRepository;
import nl.averageflow.joeswarehouse.requests.AddProductRequest;
import nl.averageflow.joeswarehouse.requests.AddProductsRequestItem;
import nl.averageflow.joeswarehouse.responses.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public final class ProductService {

    @Autowired
    private ProductRepository productRepository;

    private static List<Product> convertAddProductRequestToProductList(List<AddProductsRequestItem> rawItems) {
        return rawItems.stream().map(ProductService::productRequestItemConverter).collect(Collectors.toList());
    }

    private static Product productRequestItemConverter(AddProductsRequestItem item) {
        return new Product(item);
    }

    public ProductResponse getProducts() {
        return new ProductResponse(this.productRepository.findAll());
    }

    public Optional<Product> getProductByID(Long id) {
        return this.productRepository.findById(id);
    }

    public void deleteProductByID(Long id) {
        this.productRepository.deleteById(id);
    }

    public void addProducts(AddProductRequest request) {
        List<Product> convertedProducts = convertAddProductRequestToProductList(request.getProducts());
        this.productRepository.saveAll(convertedProducts);
    }
}
