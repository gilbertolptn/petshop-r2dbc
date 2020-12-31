package com.example.demo.petshop.animal;

import com.example.demo.petshop.TestDatabaseInitializer;
import com.example.demo.petshop.config.DatabaseInitializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
@AutoConfigureWebTestClient
@Import(TestDatabaseInitializer.class)
@ActiveProfiles("test")
class AnimalE2ETest {

    @Autowired
    private WebTestClient webClient;

    @MockBean
    DatabaseInitializer dontUseThisDatabaseInitializer;

    @BeforeEach
    void setUp() {
    }

    @Test
    void shouldReturlAllAnimals() {
        webClient.get()
                .uri("/animals")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.size()").isEqualTo(3)
                .jsonPath("$.[0].id").isEqualTo(1)
                .jsonPath("$.[0].name").isEqualTo("Rex")
                .jsonPath("$.[1].id").isEqualTo(2)
                .jsonPath("$.[1].name").isEqualTo("Max")
                .jsonPath("$.[2].id").isEqualTo(3)
                .jsonPath("$.[2].name").isEqualTo("Lucy")
        ;
    }

    @Test
    void shouldReturlOnlyWithNameRex() {
        webClient.get()
                .uri("/animals?name=Lucy")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.size()").isEqualTo(1)
                .jsonPath("$.[0].id").isEqualTo(3)
                .jsonPath("$.[0].name").isEqualTo("Lucy")
        ;
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme