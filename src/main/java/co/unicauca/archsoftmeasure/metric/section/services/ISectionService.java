package co.unicauca.archsoftmeasure.metric.section.services;

import co.unicauca.archsoftmeasure.metric.section.dominio.response.SectionResponseDTO;
import co.unicauca.archsoftmeasure.util.response.Response;

import java.util.List;

public interface ISectionService {
    public Response<List<SectionResponseDTO>> getAllSections();
}
