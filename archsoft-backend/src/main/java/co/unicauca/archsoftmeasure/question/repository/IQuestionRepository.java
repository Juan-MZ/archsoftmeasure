package co.unicauca.archsoftmeasure.question.repository;

import co.unicauca.archsoftmeasure.question.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IQuestionRepository extends JpaRepository<Question, Integer> {
}
