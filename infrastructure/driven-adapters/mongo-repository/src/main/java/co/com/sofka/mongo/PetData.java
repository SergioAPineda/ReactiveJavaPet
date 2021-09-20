package co.com.sofka.mongo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("pets")
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class PetData {
    private String id;
    private String name;
    private String kind;
    private Integer age;
}
