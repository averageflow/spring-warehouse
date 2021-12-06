package nl.averageflow.joeswarehouse.models;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nl.averageflow.joeswarehouse.repositories.ProductRepository;
import nl.averageflow.joeswarehouse.requests.AddProductRequest;
import nl.averageflow.joeswarehouse.responses.ProductResponse;

@Service
public final class ProductService {

    @Autowired
    private ProductRepository productRepository;

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
        this.productRepository.saveAll(request.getProducts());
    }
}
