package co.unicauca.archsoftmeasure.measurement.services;

import co.unicauca.archsoftmeasure.answer.model.Answer;
import co.unicauca.archsoftmeasure.answer.repository.IAnswerRepository;
import co.unicauca.archsoftmeasure.measurement.dominio.request.MeasurementRequestDTO;
import co.unicauca.archsoftmeasure.measurement.dominio.request.MeasurementSendAnswersDTO;
import co.unicauca.archsoftmeasure.measurement.dominio.response.MeasurementResponseDTO;
import co.unicauca.archsoftmeasure.measurement.model.Measurement;
import co.unicauca.archsoftmeasure.measurement.repository.IMeasurementRepository;
import co.unicauca.archsoftmeasure.test.model.Test;
import co.unicauca.archsoftmeasure.test.repository.ITestRepository;
import co.unicauca.archsoftmeasure.util.exception.ServiceRuleException;
import co.unicauca.archsoftmeasure.util.response.Response;
import co.unicauca.archsoftmeasure.util.response.handler.ResponseHandler;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MeasurementService implements IMeasurementService {
    private final IMeasurementRepository iMeasurementRepository;
    private final ITestRepository iTestRepository;
    private final IAnswerRepository iAnswerRepository;
    private final ModelMapper modelMapper;

    public MeasurementService(IMeasurementRepository iMeasurementRepository, ITestRepository iTestRepository, IAnswerRepository iAnswerRepository, ModelMapper modelMapper) {
        this.iMeasurementRepository = iMeasurementRepository;
        this.iTestRepository = iTestRepository;
        this.iAnswerRepository = iAnswerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Response<MeasurementResponseDTO> createMeasurement(String mail, Integer testId) {
        Optional<Test> test = iTestRepository.findById(testId);
        if (test.isEmpty()) {
            throw new ServiceRuleException("test.is.not.found");
        }

        Measurement measurement = new Measurement();
        measurement.setMail(mail);
        measurement.setStartDateTime(LocalDateTime.now());

        MeasurementResponseDTO measurementResponseDTO = modelMapper.map(iMeasurementRepository.save(measurement), MeasurementResponseDTO.class);

        return new ResponseHandler<>(200, "Medición creada con éxito.","http://localhost:8080/measurement/createMeasurement/{mail}/{testId}",measurementResponseDTO).getResponse();
    }

    @Override
    public Response<MeasurementResponseDTO> sendAnswersToMeasurement(Integer measurementId, MeasurementSendAnswersDTO measurementRequestDTO) {
        if(measurementRequestDTO == null||measurementRequestDTO.getAnswers() == null||measurementRequestDTO.getAnswers().isEmpty()) {
            throw new ServiceRuleException("list.of.answer.is.empty");
        }

        Optional<Measurement> measurement = iMeasurementRepository.findById(measurementId);
        if(measurement.isEmpty()) {
            throw new ServiceRuleException("measurement.is.not.found");
        }

        Measurement measurementEntity = measurement.get();
        List<Answer> answerList = new ArrayList<>();
        for(Integer answerId : measurementRequestDTO.getAnswers()) {
            Optional<Answer> answerOptional = this.iAnswerRepository.findById(answerId);
            if(answerOptional.isEmpty()) {
                throw new ServiceRuleException("answer.not.found.from.list");
            }
            answerList.add(answerOptional.get());
        }

        measurementEntity.setFinalDateTime(LocalDateTime.now());
        measurementEntity.setAnswers(answerList);

        MeasurementResponseDTO measurementResponseDTO = modelMapper.map(iMeasurementRepository.save(measurementEntity), MeasurementResponseDTO.class);

        return new ResponseHandler<>(200,"Las respuestas han sido enviadas exitosamente.","http://localhost:8080/measurement/sendAnswersToMeasurement/{measurementId}",measurementResponseDTO).getResponse();
    }

}
