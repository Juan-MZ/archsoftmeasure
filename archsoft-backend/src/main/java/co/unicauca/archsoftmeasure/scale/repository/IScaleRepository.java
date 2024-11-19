package co.unicauca.archsoftmeasure.scale.repository;

import co.unicauca.archsoftmeasure.metric.model.Metric;
import co.unicauca.archsoftmeasure.scale.model.Scale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IScaleRepository extends JpaRepository<Scale, Integer> {
    List<Scale> findAllByMetric(Metric metric);
}
