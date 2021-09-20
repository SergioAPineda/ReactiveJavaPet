package co.com.sofka.api;
import co.com.sofka.model.pet.Pet;
import co.com.sofka.usecase.pet.PetUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class ApiRest {
    private final PetUseCase useCase;

    @GetMapping(path = "/all")
    public Flux<Pet> getAllPetsRest() {
        return useCase.getAllPets();
    }

    @PostMapping(path = "/createPet")
    public Mono<Pet> getAllPetsRest(@RequestBody Pet pet) {
        return useCase.createPet(pet);
    }
}
