package co.unicauca.archsoftmeasure.metric.services;

import co.unicauca.archsoftmeasure.metric.dominio.response.MetricResponseDTO;
import co.unicauca.archsoftmeasure.util.response.Response;

import java.util.List;

public interface IMetricService {
    public Response<List<MetricResponseDTO>> getAllMetrics();
    public Response<List<MetricResponseDTO>> getAllMetricsBySection(Integer sectionId);
}
