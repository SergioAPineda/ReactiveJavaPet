package co.com.sofka.model.pet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Pet {
    private String id;
    private String name;
    private String kind;
    private Integer age;
}
