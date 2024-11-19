package co.unicauca.archsoftmeasure.scale.controller;

import co.unicauca.archsoftmeasure.scale.dominio.request.ScaleRequestDTO;
import co.unicauca.archsoftmeasure.scale.dominio.response.ScaleResponseDTO;
import co.unicauca.archsoftmeasure.scale.services.IScaleService;
import co.unicauca.archsoftmeasure.util.response.Response;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/scale")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ScaleController {
    private final IScaleService iScaleService;

    public ScaleController(IScaleService iScaleService) {
        this.iScaleService = iScaleService;
    }

    @GetMapping("/getScalesByMetric/{metricId}")
    public Response<List<ScaleResponseDTO>> getScalesByMetricId(@PathVariable final Integer metricId) {
        return iScaleService.getScalesByMetricId(metricId);
    }

    @PostMapping("/createScale/{metricId}")
    public Response<Boolean> createScale(@PathVariable final Integer metricId, @RequestBody final ScaleRequestDTO scaleRequestDTO) {
        return iScaleService.createScale(metricId, scaleRequestDTO);
    }
}
