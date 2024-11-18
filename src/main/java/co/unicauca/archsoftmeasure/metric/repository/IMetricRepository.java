package co.unicauca.archsoftmeasure.metric.repository;

import co.unicauca.archsoftmeasure.metric.model.Metric;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMetricRepository extends JpaRepository<Metric, Integer> {
}
