package co.com.sofka.api;

import co.com.sofka.model.pet.Pet;
import co.com.sofka.usecase.pet.PetUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class Handler {

  private final PetUseCase useCase;

    public Mono<ServerResponse> listenGET(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .body(useCase.getAllPets(), Pet.class);
    }

    public Mono<ServerResponse> getPetsById(ServerRequest serverRequest) {

        String id = serverRequest.pathVariable("id");
        return useCase.getPetById(id).flatMap(currentPet -> ServerResponse
                .ok()
                .body(BodyInserters.fromValue(currentPet)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> createPets(ServerRequest serverRequest){
        Mono<Pet> pet = serverRequest.bodyToMono(Pet.class);

        return pet.flatMap(currentPet -> {
            return useCase.createPet(currentPet);
        }).flatMap(currentPet -> ServerResponse.created(URI
                .create("/api/newPet".concat(currentPet.getId())))
                .body(BodyInserters.fromValue(currentPet)));

    }

    public Mono<ServerResponse> updatepets(ServerRequest request){
        Mono<Pet> pet = request.bodyToMono(Pet.class);
        String id = request.pathVariable("id");

        return pet.flatMap(currentPet -> {
            return useCase.updatePet(id, currentPet);
        }).flatMap(currentPet -> ServerResponse.created(URI
                        .create("/api/newPet".concat(currentPet.getId())))
                .body(BodyInserters.fromValue(currentPet)))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> deletePets(ServerRequest request){
        String id = request.pathVariable("id");

        return useCase.deletePets(id).then(ServerResponse.noContent().build())
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
