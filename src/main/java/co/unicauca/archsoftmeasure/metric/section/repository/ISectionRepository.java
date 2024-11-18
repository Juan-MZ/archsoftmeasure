package co.unicauca.archsoftmeasure.metric.section.repository;

import co.unicauca.archsoftmeasure.metric.section.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISectionRepository extends JpaRepository<Section, Integer> {
}
