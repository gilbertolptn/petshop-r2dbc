package com.example.demo.petshop.client;

import com.example.demo.petshop.TestDatabaseInitializer;
import com.example.demo.petshop.config.DatabaseInitializer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import reactor.test.StepVerifier;

@DataR2dbcTest
@Import({ClientRepository.class, TestDatabaseInitializer.class})
class ClientRepositoryTest {

    @Autowired
    private ClientRepository userRepository;

    @Autowired
    private R2dbcEntityTemplate entityTemplate;

    @Test
    void shouldReturnAllNames(){
        userRepository.findAllNames()
                .as(StepVerifier::create)
                .expectNext("Darcey Fifoot")
                .expectNext("Giulio Fountain")
                .expectNext("Warden Rojel")
                .expectNext("Trescha Rojel")
                .expectNext("Joaquin Haldenby")
                .verifyComplete();
    }

    @Test
    void shouldReturnOneById(){
        Long giulioId = entityTemplate
                .select(Client.class)
                .matching(Query.query(Criteria.where("firstName").is("Giulio")))
                .one().map(Client::getId).block();

        userRepository.findById(giulioId)
                .map(Client::getFullName)
                .as(StepVerifier::create)
                .expectNext("Giulio Fountain")
                .verifyComplete();
    }

    @Test
    void shouldReturnNameLikeRojel(){
        userRepository.findByName("Rojel")
                .map(Client::getFullName)
                .as(StepVerifier::create)
                .expectNext("Warden Rojel", "Trescha Rojel")
                .verifyComplete();
    }


}