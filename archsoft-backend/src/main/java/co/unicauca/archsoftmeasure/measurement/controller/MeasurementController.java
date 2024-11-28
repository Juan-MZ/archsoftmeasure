package co.unicauca.archsoftmeasure.measurement.controller;

import co.unicauca.archsoftmeasure.measurement.dominio.request.MeasurementSendAnswersDTO;
import co.unicauca.archsoftmeasure.measurement.dominio.response.MeasurementResponseDTO;
import co.unicauca.archsoftmeasure.measurement.dominio.response.ScoresResponseDTO;
import co.unicauca.archsoftmeasure.measurement.services.IMeasurementService;
import co.unicauca.archsoftmeasure.util.response.Response;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/measurement")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MeasurementController {
    private final IMeasurementService iMeasurementService;

    public MeasurementController(IMeasurementService iMeasurementService) {
        this.iMeasurementService = iMeasurementService;
    }

    @PostMapping("/createMeasurement/{mail}/{testId}")
    public Response<MeasurementResponseDTO> createMeasurement(@PathVariable final String mail, @PathVariable Integer testId) {
        return iMeasurementService.createMeasurement(mail, testId);
    }

    @PatchMapping("/sendAnswersToMeasurement/{measurementId}")
    public Response<MeasurementResponseDTO> sendAnswersToMeasurement(@PathVariable final Integer measurementId, @RequestBody MeasurementSendAnswersDTO measurementRequestDTO) {
        return iMeasurementService.sendAnswersToMeasurement(measurementId, measurementRequestDTO);
    }

    @GetMapping("/calculateScores/{measurementId}")
    public Response<ScoresResponseDTO> calculateScores(@PathVariable final Integer measurementId) {
        return iMeasurementService.calculateScores(measurementId);
    }

    @GetMapping("/getMeasurementsByEmail/{mail}")
    public Response<List<MeasurementResponseDTO>> getMeasurementsByEmail(@PathVariable String mail) {
        return iMeasurementService.getMeasurementsByEmail(mail);
    }
}
