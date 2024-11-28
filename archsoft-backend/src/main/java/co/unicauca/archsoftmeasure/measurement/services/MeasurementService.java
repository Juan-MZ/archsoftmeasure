package co.unicauca.archsoftmeasure.measurement.services;

import co.unicauca.archsoftmeasure.answer.model.Answer;
import co.unicauca.archsoftmeasure.answer.repository.IAnswerRepository;
import co.unicauca.archsoftmeasure.measurement.dominio.request.MeasurementRequestDTO;
import co.unicauca.archsoftmeasure.measurement.dominio.request.MeasurementSendAnswersDTO;
import co.unicauca.archsoftmeasure.measurement.dominio.response.MeasurementResponseDTO;
import co.unicauca.archsoftmeasure.measurement.dominio.response.ScoresResponseDTO;
import co.unicauca.archsoftmeasure.measurement.model.Measurement;
import co.unicauca.archsoftmeasure.measurement.repository.IMeasurementRepository;
import co.unicauca.archsoftmeasure.metric.model.Metric;
import co.unicauca.archsoftmeasure.metric.repository.IMetricRepository;
import co.unicauca.archsoftmeasure.metric.section.model.Section;
import co.unicauca.archsoftmeasure.metric.section.repository.ISectionRepository;
import co.unicauca.archsoftmeasure.scale.model.Scale;
import co.unicauca.archsoftmeasure.scale.repository.IScaleRepository;
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
    private final ISectionRepository iSectionRepository;
    private final IAnswerRepository iAnswerRepository;
    private final IScaleRepository iScaleRepository;
    private final ModelMapper modelMapper;

    public MeasurementService(IMeasurementRepository iMeasurementRepository, ITestRepository iTestRepository, ISectionRepository iSectionRepository, IAnswerRepository iAnswerRepository, IScaleRepository iScaleRepository, ModelMapper modelMapper) {
        this.iMeasurementRepository = iMeasurementRepository;
        this.iTestRepository = iTestRepository;
        this.iSectionRepository = iSectionRepository;
        this.iAnswerRepository = iAnswerRepository;
        this.iScaleRepository = iScaleRepository;
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
        measurement.setTest(test.get());
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

    @Override
    public Response<ScoresResponseDTO> calculateScores(Integer measurementId) {
        Optional<Measurement> measurement = iMeasurementRepository.findById(measurementId);
        if(measurement.isEmpty()) {
            throw new ServiceRuleException("measurement.is.not.found");
        }
        Measurement measurementEntity = measurement.get();
        ScoresResponseDTO scoresResponseDTO = new ScoresResponseDTO();
        scoresResponseDTO.setMetricScores(new ArrayList<>());
        scoresResponseDTO.setSectionScores(new ArrayList<>());
        List<Section> sections = this.iSectionRepository.findAll();

        for(Section section : sections) {
            List<Double> metricScoresAux = new ArrayList<>();
            for(Metric metric : section.getMetrics()) {
                List<Double> scales = new ArrayList<>();
                for(Answer answer : measurementEntity.getAnswers()) {
                    Optional<Scale> scale = this.iScaleRepository.findByMetricAndAnswersContains(metric, answer);
                    scale.ifPresent(value -> scales.add(value.getPercentage()));
                }
                Double metricScore = scales.stream()
                        .mapToDouble(Double::doubleValue) // Convertir a DoubleStream
                        .filter(score -> score != -1.0) // Excluir valores iguales a -1.0
                        .average()                        // Obtener el promedio
                        .orElse(-1.0);
                scoresResponseDTO.getMetricScores().add(metricScore);
                metricScoresAux.add(metricScore);
            }
            scoresResponseDTO.getSectionScores().add(metricScoresAux.stream().mapToDouble(Double::doubleValue).filter(score -> score != -1.0).average().orElse(-1.0));
        }

        scoresResponseDTO.setGeneralScore(scoresResponseDTO.getSectionScores().stream().mapToDouble(Double::doubleValue).filter(score -> score != -1.0).average().orElse(-1.0));

        return new ResponseHandler<>(200,"Se han obtenido los resultados exitosamente.","http://localhost:8080/measurement/calculateScores/{measurementId}",scoresResponseDTO).getResponse();
    }

    @Override
    public Response<List<MeasurementResponseDTO>> getMeasurementsByEmail(String mail) {
        List<Measurement> measurements = this.iMeasurementRepository.findAllByMail(mail);
        if(measurements.isEmpty()) {
            throw new ServiceRuleException("measurements.are.not.found.by.mail");
        }
        List<MeasurementResponseDTO> measurementResponseDTOList = new ArrayList<>();
        for(Measurement measurement : measurements) {
            if (measurement.getFinalDateTime()!=null) {
                measurementResponseDTOList.add(modelMapper.map(measurement, MeasurementResponseDTO.class));
            }
        }

        return new ResponseHandler<>(200,"Se han obtenido las mediciones exitosamente.","http://localhost:8080/measurement/getMeasurementsByEmail/{mail}",measurementResponseDTOList).getResponse();
    }

}
