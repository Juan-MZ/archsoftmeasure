package co.unicauca.archsoftmeasure.test.dominio.response;

import co.unicauca.archsoftmeasure.question.dominio.response.QuestionResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestResponseDTO {
    private Integer testId;
    private String name;
    private List<QuestionResponseDTO> questions;
}
