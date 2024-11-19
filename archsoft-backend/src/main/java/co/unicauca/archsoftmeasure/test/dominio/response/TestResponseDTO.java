package co.unicauca.archsoftmeasure.test.dominio.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestResponseDTO {
    private Integer testId;
    private String name;
}
