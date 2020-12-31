package com.example.demo.petshop.animal;

import com.example.demo.petshop.TestDatabaseInitializer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.context.annotation.Import;
import reactor.test.StepVerifier;

import java.time.LocalDate;

@DataR2dbcTest
@Import({TestDatabaseInitializer.class})
class AnimalRepositoryTest {

    @Autowired
    private AnimalRepository animalRepository;

    @Test
    void shouldReturnAllAnimals(){
        animalRepository.findAll()
                .as(StepVerifier::create)
                .expectNext(new Animal(1L, "Rex", LocalDate.parse("2020-01-01")))
                .expectNext(new Animal(2L, "Max", LocalDate.parse("2019-08-01")))
                .expectNext(new Animal(3L, "Lucy", LocalDate.parse("2018-05-01")))
        .verifyComplete();
    }

    @Test
    void shouldReturnByNameOnlyMax(){
        animalRepository.findAllByName("Max")
                .as(StepVerifier::create)
                .expectNext(new Animal(2L, "Max", LocalDate.parse("2019-08-01")))
                .verifyComplete();
    }
}