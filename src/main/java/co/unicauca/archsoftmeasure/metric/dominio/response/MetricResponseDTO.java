package co.unicauca.archsoftmeasure.metric.dominio.response;

import co.unicauca.archsoftmeasure.metric.section.model.Section;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
