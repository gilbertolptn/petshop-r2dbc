package com.example.demo.petshop.product;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    private static final Product SOME_PRODUCT = new Product(1L, "Product A");

    @Mock
    ProductRepository productRepository;
    @InjectMocks
    ProductService productService;

    @Test
    void testListProducts() {
        when(productRepository.listAll()).thenReturn(Flux.just(SOME_PRODUCT));

        productService.listProducts()
        .as(StepVerifier::create)
        .expectNext(SOME_PRODUCT)
        .verifyComplete();

        verify(productRepository).listAll();
        verifyNoMoreInteractions(productRepository);
    }
}