package co.unicauca.archsoftmeasure.metric.services;

import co.unicauca.archsoftmeasure.metric.dominio.response.MetricResponseDTO;

import java.util.List;

public interface IMetricService {
    public List<MetricResponseDTO> getAllMetrics();
    public List<MetricResponseDTO> getAllMetricsBySection(Integer sectionId);
}
