package com.example.demo.petshop.product;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    protected Flux<Product> listProducts() {
        return productRepository.listAll();
    }
}
