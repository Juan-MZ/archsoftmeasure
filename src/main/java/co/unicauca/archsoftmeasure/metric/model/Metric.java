package co.unicauca.archsoftmeasure.metric.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "metric")
public class Metric {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "metric_id_seq")
    @SequenceGenerator(name = "metric_id_seq", sequenceName = "metric_id_seq", allocationSize = 1)
    @Column(name = "metric_id")
    private Integer metricId;

    private String name;
}
