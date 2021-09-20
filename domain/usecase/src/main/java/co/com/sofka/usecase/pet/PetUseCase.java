package co.com.sofka.usecase.pet;

import co.com.sofka.model.pet.Pet;
import co.com.sofka.model.pet.gateways.PetRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class PetUseCase {
    private final PetRepository serviceGateway;

    public Mono<Pet> createPet(Pet pet){
        return serviceGateway.create(pet);
    }

    public Flux<Pet> getAllPets(){ return serviceGateway.getAll(); }

    public Mono<Pet> getPetById(String id){ return serviceGateway.getPetsById(id); }

    public Mono<Pet> updatePet(String id, Pet pet){
        return serviceGateway.updatePet(id, pet);
    }

    public Mono<Void> deletePets(String id){
        return serviceGateway.deletePet(id);
    }
}
