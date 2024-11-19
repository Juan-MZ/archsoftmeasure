package co.unicauca.archsoftmeasure.metric.section.dominio.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SectionResponseDTO {
    private Integer sectionId;
    private String name;
}
