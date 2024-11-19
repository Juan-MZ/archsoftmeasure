package co.unicauca.archsoftmeasure.metric.controller;

import co.unicauca.archsoftmeasure.metric.dominio.response.MetricResponseDTO;
import co.unicauca.archsoftmeasure.metric.services.IMetricService;
import co.unicauca.archsoftmeasure.util.response.Response;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/metric")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MetricController {

    private final IMetricService iMetricService;

    public MetricController(IMetricService iMetricService) {
        this.iMetricService = iMetricService;
    }

    @GetMapping("/getAllMetrics")
    public Response<List<MetricResponseDTO>> getAllMetrics() {
        return iMetricService.getAllMetrics();
    }

    @GetMapping("getAllMetricsBySection/{sectionId}")
    public Response<List<MetricResponseDTO>> getAllMetricsBySection(@PathVariable final Integer sectionId) {
        return iMetricService.getAllMetricsBySection(sectionId);
    }
}
