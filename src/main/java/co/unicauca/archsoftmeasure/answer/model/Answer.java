package co.unicauca.archsoftmeasure.answer.model;

import co.unicauca.archsoftmeasure.question.model.Question;
import jakarta.persistence.*;
import lombok.*;

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

    @Column(name = "answer_statement")
    private String statement;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "answer_question")
    private Question question;
}
