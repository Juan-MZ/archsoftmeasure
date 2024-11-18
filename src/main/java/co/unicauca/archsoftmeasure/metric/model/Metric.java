package co.unicauca.archsoftmeasure.metric.model;

import co.unicauca.archsoftmeasure.scale.model.Scale;
import co.unicauca.archsoftmeasure.metric.section.model.Section;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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

    @Column(name = "metric_name", unique = true, nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "metric_section")
    private Section section;

    @OneToMany(mappedBy = "metric")
    private List<Scale> scales;



}
