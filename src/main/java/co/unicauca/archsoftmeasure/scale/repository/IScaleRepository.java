package co.unicauca.archsoftmeasure.scale.repository;

import co.unicauca.archsoftmeasure.scale.model.Scale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IScaleRepository extends JpaRepository<Scale, Integer> {
}
