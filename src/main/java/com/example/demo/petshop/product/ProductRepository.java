package com.example.demo.petshop.product;

import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public class ProductRepository {

    private final DatabaseClient databaseClient;

    public ProductRepository(DatabaseClient databaseClient) {
        this.databaseClient = databaseClient;
    }

    protected Flux<Product> listAll() {

        return databaseClient
                .sql("SELECT id, name FROM Product")
                .map(row -> new Product(
                        row.get("id", Long.class),
                        row.get("name", String.class))
                ).all();
    }
}
