package co.unicauca.archsoftmeasure.measurement.services;

import co.unicauca.archsoftmeasure.measurement.dominio.request.MeasurementRequestDTO;
import co.unicauca.archsoftmeasure.measurement.dominio.request.MeasurementSendAnswersDTO;
import co.unicauca.archsoftmeasure.measurement.dominio.response.MeasurementResponseDTO;
import co.unicauca.archsoftmeasure.measurement.dominio.response.ScoresResponseDTO;
import co.unicauca.archsoftmeasure.util.response.Response;

public interface IMeasurementService {
    public Response<MeasurementResponseDTO> createMeasurement(String mail, Integer testId);
    public Response<MeasurementResponseDTO> sendAnswersToMeasurement(Integer measurementId, MeasurementSendAnswersDTO measurementRequestDTO);
    public Response<ScoresResponseDTO> calculateScores(Integer measurementId);
}
