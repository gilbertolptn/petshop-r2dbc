package com.example.demo.petshop.client;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Flux<String> findAllNames(){
        return clientRepository.findAllNames();
    }

    public Flux<Client> findNameLike(String name){
        return clientRepository.findByName(name);
    }

    public Mono<Client> findById(Long id){
        return clientRepository.findById(id);
    }




    }
