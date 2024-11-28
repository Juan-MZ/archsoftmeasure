package co.unicauca.archsoftmeasure.measurement.repository;

import co.unicauca.archsoftmeasure.measurement.model.Measurement;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IMeasurementRepository extends JpaRepository<Measurement, Integer> {
    List<Measurement> findAllByMail(String mail);
}
