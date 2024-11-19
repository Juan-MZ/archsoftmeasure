package co.unicauca.archsoftmeasure.scale.dominio.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScaleRequestDTO {
    private Double percentage;
}
