package co.unicauca.archsoftmeasure.measurement.repository;

import co.unicauca.archsoftmeasure.measurement.model.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMeasurementRepository extends JpaRepository<Measurement, Integer> {
}
