package com.example.demo.petshop.animal;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class AnimalService {
    
    private AnimalRepository animalRepository;

    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }


    protected Flux<Animal> listAnimals(String name) {
        if(name != null && !name.isBlank()){
            return animalRepository.findAllByName(name);
        }else {
            return animalRepository.findAll();
        }
    }

}
