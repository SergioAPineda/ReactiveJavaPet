package co.com.sofka.config;

import co.com.sofka.model.pet.gateways.PetRepository;
import co.com.sofka.usecase.pet.PetUseCase;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = "co.com.sofka.usecase",
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "^.+UseCase$")
        },
        useDefaultFilters = false)
public class UseCasesConfig {
        public PetUseCase petUseCase(PetRepository petRepository){
                return new PetUseCase(petRepository);
        }
}
