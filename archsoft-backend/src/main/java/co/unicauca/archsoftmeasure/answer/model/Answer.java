package co.unicauca.archsoftmeasure.answer.model;

import co.unicauca.archsoftmeasure.measurement.model.Measurement;
import co.unicauca.archsoftmeasure.question.model.Question;
import co.unicauca.archsoftmeasure.scale.model.Scale;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "answer")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "answer_id_seq")
    @SequenceGenerator(name = "answer_id_seq", sequenceName = "answer_id_seq", allocationSize = 1)
    @Column(name = "answer_id")
    private Integer answerId;

    @Column(name = "answer_statement", nullable = false, length = 1500)
    private String statement;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "answer_question")
    private Question question;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private List<Scale> scales;

    @ManyToMany(mappedBy = "answers")
    private List<Measurement> measurements;
}
