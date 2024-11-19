package co.unicauca.archsoftmeasure.metric.section.controller;

import co.unicauca.archsoftmeasure.metric.section.dominio.response.SectionResponseDTO;
import co.unicauca.archsoftmeasure.metric.section.services.ISectionService;
import co.unicauca.archsoftmeasure.util.response.Response;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/section")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SectionController {
    private final ISectionService iSectionService;

    public SectionController(ISectionService iSectionService) {
        this.iSectionService = iSectionService;
    }

    @GetMapping("/getAllSections")
    public Response<List<SectionResponseDTO>> getAllSections() {
        return iSectionService.getAllSections();
    }
}
