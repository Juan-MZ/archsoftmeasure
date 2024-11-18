package co.unicauca.archsoftmeasure.metric.section.model;

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
@Table(name = "section")
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "section_id_seq")
    @SequenceGenerator(name = "section_id_seq", sequenceName = "section_id_seq", allocationSize = 1)
    @Column(name = "section_id")
    private Integer sectionId;

    @Column(name = "section_name", unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "section")
    List<Metric> metrics;
}
