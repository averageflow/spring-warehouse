package nl.averageflow.joeswarehouse.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import nl.averageflow.joeswarehouse.products.Product;
import nl.averageflow.joeswarehouse.products.ProductRepository;

final class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> getProducts() {
        return this.repository.findAll();
    }
}
