package co.unicauca.archsoftmeasure.metric.repository;

import co.unicauca.archsoftmeasure.metric.model.Metric;
import co.unicauca.archsoftmeasure.metric.section.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IMetricRepository extends JpaRepository<Metric, Integer> {
    List<Metric> findAllBySection(Section section);
}
