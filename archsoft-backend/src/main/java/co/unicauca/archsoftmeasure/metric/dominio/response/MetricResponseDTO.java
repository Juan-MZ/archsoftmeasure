package co.unicauca.archsoftmeasure.metric.dominio.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MetricResponseDTO {
    private Integer metricId;
    private String name;
}
