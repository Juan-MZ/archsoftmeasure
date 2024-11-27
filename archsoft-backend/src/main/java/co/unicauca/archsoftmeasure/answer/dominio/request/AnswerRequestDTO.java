package co.unicauca.archsoftmeasure.answer.dominio.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnswerRequestDTO {
    private String statement;
    private List<Double> metricScaleValues;
}
