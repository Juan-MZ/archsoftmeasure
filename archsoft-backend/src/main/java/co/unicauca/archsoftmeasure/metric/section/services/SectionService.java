package co.unicauca.archsoftmeasure.metric.section.services;

import co.unicauca.archsoftmeasure.metric.section.dominio.response.SectionResponseDTO;
import co.unicauca.archsoftmeasure.metric.section.model.Section;
import co.unicauca.archsoftmeasure.metric.section.repository.ISectionRepository;
import co.unicauca.archsoftmeasure.util.exception.ServiceRuleException;
import co.unicauca.archsoftmeasure.util.response.Response;
import co.unicauca.archsoftmeasure.util.response.handler.ResponseHandler;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionService implements ISectionService {
    private final ISectionRepository iSectionRepository;
    private final ModelMapper modelMapper;

    public SectionService(ISectionRepository iSectionRepository, ModelMapper modelMapper) {
        this.iSectionRepository = iSectionRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Response<List<SectionResponseDTO>> getAllSections() {
        List<Section> sections = iSectionRepository.findAll();
        if (sections.isEmpty()) {
            throw new ServiceRuleException("");
        }

        List<SectionResponseDTO> sectionResponseDTOS = sections.stream().map(section -> modelMapper.map(section, SectionResponseDTO.class)).toList();

        return new ResponseHandler<>(200, "Secciones encontradas con éxito.","http://localhost:8080/section/getAllSections",sectionResponseDTOS).getResponse();
    }
}
