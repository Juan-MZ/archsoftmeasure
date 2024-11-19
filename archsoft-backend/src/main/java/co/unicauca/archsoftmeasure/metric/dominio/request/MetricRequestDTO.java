package co.unicauca.archsoftmeasure.metric.dominio.request;

import co.unicauca.archsoftmeasure.metric.section.model.Section;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MetricRequestDTO {
    private Integer metricId;
    private String name;
}
