package co.unicauca.archsoftmeasure.test.dominio.request;

import co.unicauca.archsoftmeasure.question.dominio.request.QuestionRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestRequestDTO {
    private String name;
    private List<QuestionRequestDTO> questions;
}
