package co.com.sofka.model.pet.gateways;

import co.com.sofka.model.pet.Pet;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PetRepository {
    Mono<Pet> create(Pet pet);
    Flux<Pet> getAll();
    Mono<Pet> getPetsById(String id);
    Mono<Pet> updatePet(String id, Pet pet);
    Mono<Void> deletePet(String id);

}
