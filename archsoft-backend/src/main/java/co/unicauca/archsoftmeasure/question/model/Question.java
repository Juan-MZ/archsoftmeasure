package co.unicauca.archsoftmeasure.question.model;

import co.unicauca.archsoftmeasure.answer.model.Answer;
import co.unicauca.archsoftmeasure.test.model.Test;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "question_id_seq")
    @SequenceGenerator(name = "question_id_seq", sequenceName = "question_id_seq", allocationSize = 1)
    @Column(name = "question_id")
    private Integer questionId;

    @Column(name = "question_statement", nullable = false, length = 1500)
    private String statement;

    @ManyToMany(mappedBy = "questions")
    private List<Test> tests;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Answer> answers;

}
