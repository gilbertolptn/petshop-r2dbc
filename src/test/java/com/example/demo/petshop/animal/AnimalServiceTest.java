package com.example.demo.petshop.animal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AnimalServiceTest {

    private final Animal REX = new Animal(1L, "Rex", LocalDate.parse("2021-01-01"));
    private final Animal LUCY = new Animal(1L, "Lucy", LocalDate.parse("2020-08-01"));
    @Mock
    AnimalRepository animalRepository;

    @InjectMocks
    AnimalService animalService;

    @Test
    void shouldFindAll() {
        when(animalRepository.findAll()).thenReturn(Flux.just(REX, LUCY));

        animalService.listAnimals(null)
                .as(StepVerifier::create)
                .expectNext(REX)
                .expectNext(LUCY)
                .verifyComplete();

        verify(animalRepository).findAll();
        verifyNoMoreInteractions(animalRepository);
    }

    @Test
    void shouldFindOnlyRex() {
        when(animalRepository.findAllByName("Rex")).thenReturn(Flux.just(REX));

        animalService.listAnimals("Rex")
                .as(StepVerifier::create)
                .expectNext(REX)
                .verifyComplete();

        verify(animalRepository).findAllByName("Rex");
        verifyNoMoreInteractions(animalRepository);
    }
}