package co.unicauca.archsoftmeasure.test.repository;

import co.unicauca.archsoftmeasure.test.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITestRepository extends JpaRepository<Test, Integer> {
}
