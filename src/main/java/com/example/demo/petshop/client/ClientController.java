package com.example.demo.petshop.client;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/client-names")
    public Flux<String> findAllNames(){
        return clientService.findAllNames();
    }

    @GetMapping("/clients")
    public Flux<Client> findNameLike(@RequestParam(value = "name", required = false) String name){
        return clientService.findNameLike(name);
    }

    @GetMapping("/clients/{id}")
    public Mono<Client> findById(@PathVariable("id") Long id){
        return clientService.findById(id);
    }
}
