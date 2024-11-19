package co.unicauca.archsoftmeasure.scale.dominio.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScaleResponseDTO {
    private Integer scaleId;
    private Double percentage;
}
