package nl.averageflow.joeswarehouse.products;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> getProducts() {
        return this.repository.findAll();
    }

    public Optional<Product> getProductByID(Long id) {
        return this.repository.findById(id);
    }
}
