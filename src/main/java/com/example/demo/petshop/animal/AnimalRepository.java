package com.example.demo.petshop.animal;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface AnimalRepository extends ReactiveCrudRepository<Animal, Long> {

    @Query("select * from Animal a where a.name like :name ")
    Flux<Animal> findAllByName(@Param("name") String name);
}
