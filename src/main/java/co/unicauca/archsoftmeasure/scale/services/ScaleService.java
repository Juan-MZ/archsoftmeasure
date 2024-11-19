package co.unicauca.archsoftmeasure.scale.services;

import co.unicauca.archsoftmeasure.metric.model.Metric;
import co.unicauca.archsoftmeasure.metric.repository.IMetricRepository;
import co.unicauca.archsoftmeasure.scale.dominio.request.ScaleRequestDTO;
import co.unicauca.archsoftmeasure.scale.dominio.response.ScaleResponseDTO;
import co.unicauca.archsoftmeasure.scale.model.Scale;
import co.unicauca.archsoftmeasure.scale.repository.IScaleRepository;
import co.unicauca.archsoftmeasure.util.exception.ServiceRuleException;
import co.unicauca.archsoftmeasure.util.response.Response;
import co.unicauca.archsoftmeasure.util.response.handler.ResponseHandler;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScaleService implements IScaleService {

    private final IScaleRepository iScaleRepository;
    private final IMetricRepository iMetricRepository;
    private final ModelMapper modelMapper;

    public ScaleService(IScaleRepository iScaleRepository, IMetricRepository iMetricRepository, ModelMapper modelMapper) {
        this.iScaleRepository = iScaleRepository;
        this.iMetricRepository = iMetricRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Response<List<ScaleResponseDTO>> getScalesByMetricId(Integer metricId) {
        Optional<Metric> metricOptional = this.iMetricRepository.findById(metricId);
        if (metricOptional.isEmpty()) {
            throw new ServiceRuleException("metric.is.not.found");
        }

        List<Scale> scales = this.iScaleRepository.findAllByMetric(metricOptional.get());
        if (scales.isEmpty()) {
            throw new ServiceRuleException("scales.are.not.found");
        }

        List<ScaleResponseDTO> scaleResponseDTOS = scales.stream().map(scale -> modelMapper.map(scale, ScaleResponseDTO.class)).toList();
        return new ResponseHandler<>(200, "Escalas encontradas con éxito.","http://localhost:8080/scale/getScalesByMetric/{metricId}",scaleResponseDTOS).getResponse();
    }

    @Override
    public Response<Boolean> createScale(Integer metricId, ScaleRequestDTO scaleRequestDTO) {
        Optional<Metric> metricOptional = this.iMetricRepository.findById(metricId);
        if (metricOptional.isEmpty()) {
            throw new ServiceRuleException("metric.is.not.found");
        }

        Scale scale = new Scale();
        scale.setPercentage(scaleRequestDTO.getPercentage());
        scale.setMetric(metricOptional.get());
        this.iScaleRepository.save(scale);
        return new ResponseHandler<>(200, "Escala creada con éxito.", "http://localhost:8080/scale/createScale/{metricId}",Boolean.TRUE).getResponse();
    }

}
