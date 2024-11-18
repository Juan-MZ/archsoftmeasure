package co.unicauca.archsoftmeasure.answer.repository;

import co.unicauca.archsoftmeasure.answer.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAnswerRepository extends JpaRepository<Answer, Integer> {
}
