package co.unicauca.archsoftmeasure.question.dominio.request;

import co.unicauca.archsoftmeasure.answer.dominio.request.AnswerRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionRequestDTO {
    private String statement;
    private List<AnswerRequestDTO> answers;
    private List<Integer> metrics;
}
