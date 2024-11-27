package co.unicauca.archsoftmeasure.scale.repository;

import co.unicauca.archsoftmeasure.answer.model.Answer;
import co.unicauca.archsoftmeasure.metric.model.Metric;
import co.unicauca.archsoftmeasure.scale.model.Scale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IScaleRepository extends JpaRepository<Scale, Integer> {
    List<Scale> findAllByMetric(Metric metric);
    Optional<Scale> findByPercentageAndMetric(Double percentage, Metric metric);
    Optional<Scale> findByMetricAndAnswersContains(Metric metric, Answer answers);
}
