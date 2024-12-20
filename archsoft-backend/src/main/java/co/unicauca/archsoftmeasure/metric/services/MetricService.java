package co.unicauca.archsoftmeasure.metric.services;

import co.unicauca.archsoftmeasure.metric.dominio.response.MetricResponseDTO;
import co.unicauca.archsoftmeasure.metric.model.Metric;
import co.unicauca.archsoftmeasure.metric.repository.IMetricRepository;
import co.unicauca.archsoftmeasure.metric.section.model.Section;
import co.unicauca.archsoftmeasure.metric.section.repository.ISectionRepository;
import co.unicauca.archsoftmeasure.util.exception.ServiceRuleException;
import co.unicauca.archsoftmeasure.util.response.Response;
import co.unicauca.archsoftmeasure.util.response.handler.ResponseHandler;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MetricService implements IMetricService {

    private final IMetricRepository iMetricRepository;
    private final ISectionRepository iSectionRepository;
    private final ModelMapper modelMapper;


    public MetricService(IMetricRepository iMetricRepository, ISectionRepository iSectionRepository, ModelMapper modelMapper) {
        this.iMetricRepository = iMetricRepository;
        this.iSectionRepository = iSectionRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Response<List<MetricResponseDTO>> getAllMetrics() {
        List<Metric> metrics = iMetricRepository.findAll();
        if (metrics.isEmpty()) {
            throw new ServiceRuleException("there.arent.registered.metrics");
        }

        List<MetricResponseDTO> sectionResponseDTOS = metrics.stream()
                .map(metric -> modelMapper.map(metric, MetricResponseDTO.class))
                .toList();
        return new ResponseHandler<>(200,"Métricas encontradas con éxito.","http://localhost:8080/metric/getAllMetrics",sectionResponseDTOS).getResponse();
    }

    @Override
    public Response<List<MetricResponseDTO>> getAllMetricsBySection(Integer sectionId) {
        Optional<Section> sectionOptional = iSectionRepository.findById(sectionId);
        if (sectionOptional.isEmpty()) {
            throw new ServiceRuleException("section.is.not.found");
        }
        List<Metric> metrics = iMetricRepository.findAllBySection(sectionOptional.get());

        List<MetricResponseDTO> sectionResponseDTOS = metrics.stream()
                .map(metric -> modelMapper.map(metric, MetricResponseDTO.class))
                .toList();
        return new ResponseHandler<>(200,"Métricas encontradas con éxito.","http://localhost:8080/metric/getAllMetricsBySection/{sectionId}",sectionResponseDTOS).getResponse();
    }
}
