package co.unicauca.archsoftmeasure.metric.section.dominio.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SectionRequestDTO {
    private Integer sectionId;
    private String name;
}
