package co.unicauca.archsoftmeasure.measurement.dominio.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MeasurementSendAnswersDTO {
    List<Integer>  answers;
}
