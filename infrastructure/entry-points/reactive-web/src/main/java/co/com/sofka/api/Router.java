package co.com.sofka.api;

import co.com.sofka.model.pet.Pet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;


@Configuration
public class Router {
@Bean
public RouterFunction<ServerResponse> routerFunction(Handler handler) {
    return route(GET("/api/"), handler::listenGET)
            .andRoute(GET("/api/{id}"), handler::getPetsById)
            .andRoute(POST("/api/newPet"), handler::createPets)
            .andRoute(PUT("/api/update/{id}"), handler::updatepets)
            .andRoute(DELETE("/api/delete/{id}"), handler::deletePets);
    }
}
