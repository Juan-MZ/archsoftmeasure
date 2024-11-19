package co.unicauca.archsoftmeasure.scale.services;

import co.unicauca.archsoftmeasure.scale.dominio.request.ScaleRequestDTO;
import co.unicauca.archsoftmeasure.scale.dominio.response.ScaleResponseDTO;
import co.unicauca.archsoftmeasure.util.response.Response;

import java.util.List;

public interface IScaleService {
    public Response<List<ScaleResponseDTO>> getScalesByMetricId(Integer metricId);
    public Response<Boolean> createScale(Integer metricId, ScaleRequestDTO scaleRequestDTO);
}
