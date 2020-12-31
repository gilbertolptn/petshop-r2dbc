package com.example.demo.petshop.product;

import com.example.demo.petshop.TestDatabaseInitializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.r2dbc.core.DatabaseClient;
import reactor.test.StepVerifier;

@DataR2dbcTest
@Import({ProductRepository.class, TestDatabaseInitializer.class})
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    DatabaseClient databaseClient;

    @BeforeEach
    void setupRecords(){
        databaseClient.sql("delete from product")
                .then().as(StepVerifier::create).verifyComplete();
        databaseClient.sql("insert into product (name) values ('CACTUS GRANDIFLORUS')")
                .then().as(StepVerifier::create).verifyComplete();
    }

    @Test
    void testListAll() {
        productRepository.listAll()
                .map(Product::getName)
                .as(StepVerifier::create)
                .expectNext( "CACTUS GRANDIFLORUS")
                .verifyComplete();

//        Assertions.assertEquals(null, result);
    }
}

