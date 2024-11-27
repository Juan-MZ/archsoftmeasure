package co.unicauca.archsoftmeasure.measurement.dominio.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScoresResponseDTO {
    private Double generalScore;
    private List<Double> sectionScores;
    private List<Double> metricScores;
}
