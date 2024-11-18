package co.unicauca.archsoftmeasure.scale.model;

import co.unicauca.archsoftmeasure.answer.model.Answer;
import co.unicauca.archsoftmeasure.metric.model.Metric;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "scale")
public class Scale {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "scale_id_seq")
    @SequenceGenerator(name = "scale_id_seq", sequenceName = "scale_id_seq", allocationSize = 1)
    @Column(name = "scale_id")
    private Integer scaleId;

    @Column(name = "scale_percentage")
    private Double percentage;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "scale_metric")
    private Metric metric;

    @ManyToMany(mappedBy = "scales")
    List<Answer> answers;


}
