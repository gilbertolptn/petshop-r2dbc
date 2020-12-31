package com.example.demo.petshop.client;

import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class ClientRepository {

    private final R2dbcEntityTemplate entityTemplate;

    public ClientRepository(R2dbcEntityTemplate entityTemplate) {
        this.entityTemplate = entityTemplate;
    }

    public Flux<String> findAllNames(){
        return entityTemplate
                .select(Client.class)
                .all()
                .map(Client::getFullName);
    }

    public Flux<Client> findByName(String name){

        if(name == null){
            return entityTemplate
                    .select(Client.class)
                    .all();
        }

        return entityTemplate.select(Client.class)
                .matching(Query.query(Criteria
                        .where("firstName").like(name)
                        .or("lastName").is(name)))
                .all();
    }

    public Mono<Client> findById(Long id){
        return entityTemplate
                .select(Client.class)
                .matching(Query.query(Criteria.where("id").is(id)))
                .one();
    }
}
