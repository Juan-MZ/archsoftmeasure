package co.unicauca.archsoftmeasure.test.model;

import co.unicauca.archsoftmeasure.measurement.model.Measurement;
import co.unicauca.archsoftmeasure.question.model.Question;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "test")
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "test_id_seq")
    @SequenceGenerator(name = "test_id_seq", sequenceName = "test_id_seq", allocationSize = 1)
    @Column(name = "test_id")
    private Integer testId;

    @Column(name = "test_name")
    private String name;

    @OneToMany(mappedBy = "test")
    private List<Measurement> measurement;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private List<Question> questions;

}
