package co.com.sofka.mongo;

import co.com.sofka.model.pet.Pet;
import co.com.sofka.model.pet.gateways.PetRepository;
import co.com.sofka.mongo.helper.AdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class MongoRepositoryAdapter extends AdapterOperations<Pet, PetData, String, MongoDBRepository>
 implements PetRepository
{

    public MongoRepositoryAdapter(MongoDBRepository repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, Pet.class/* change for domain model */));
    }

    @Override
    public Mono<Pet> create(Pet pet) {
        PetData petObject = PetData.builder().id(pet.getId()).name(pet.getName()).kind(pet.getKind()).age(pet.getAge()).build();
        return repository.save(petObject).map(this::toEntity);
    }

    @Override
    public Flux<Pet> getAll() {
        return this.repository.findAll().map(this::toEntity);
    }

    @Override
    public Mono<Pet> getPetsById(String id) {
        return repository.findById(id).map(this::toEntity);
    }

    @Override
    public Mono<Pet> updatePet(String id, Pet pet) {
        return repository.findById(id)
                .flatMap(currentPet   -> {
                    currentPet.setName(pet.getName());
                    currentPet.setAge(pet.getAge());
                    currentPet.setKind(pet.getKind());
                    return repository.save(currentPet).map(this::toEntity);
                });
    }

    @Override
    public Mono<Void> deletePet(String id) {
        return repository.deleteById(id);
    }
}
