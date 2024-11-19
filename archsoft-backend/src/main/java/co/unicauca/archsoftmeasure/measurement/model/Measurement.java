package co.unicauca.archsoftmeasure.measurement.model;

import co.unicauca.archsoftmeasure.answer.model.Answer;
import co.unicauca.archsoftmeasure.test.model.Test;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "measurement")
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "measurement_id_seq")
    @SequenceGenerator(name = "measurement_id_seq", sequenceName = "measurement_id_seq", allocationSize = 1)
    @Column(name = "measurement_id")
    private Integer measurementId;

    @Column(name = "measurement_mail")
    private String mail;

    @Column(name = "start_date_time")
    private LocalDateTime startDateTime;

    @Column(name = "final_date_time")
    private LocalDateTime finalDateTime;

    @Column(name = "global_score")
    private Double globalScore;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "measurement_test")
    private Test test;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    List<Answer> answers;
}
