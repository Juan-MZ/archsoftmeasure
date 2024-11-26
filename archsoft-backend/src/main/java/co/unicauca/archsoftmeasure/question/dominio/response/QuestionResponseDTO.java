package co.unicauca.archsoftmeasure.question.dominio.response;

import co.unicauca.archsoftmeasure.answer.dominio.response.AnswerResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionResponseDTO {
    private Integer questionId;
    private String statement;
    private List<AnswerResponseDTO> answers;
}
