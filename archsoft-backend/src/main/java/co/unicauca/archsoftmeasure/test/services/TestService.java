package co.unicauca.archsoftmeasure.test.services;

import co.unicauca.archsoftmeasure.answer.dominio.request.AnswerRequestDTO;
import co.unicauca.archsoftmeasure.answer.dominio.response.AnswerResponseDTO;
import co.unicauca.archsoftmeasure.answer.model.Answer;
import co.unicauca.archsoftmeasure.metric.model.Metric;
import co.unicauca.archsoftmeasure.metric.repository.IMetricRepository;
import co.unicauca.archsoftmeasure.question.dominio.request.QuestionRequestDTO;
import co.unicauca.archsoftmeasure.question.dominio.response.QuestionResponseDTO;
import co.unicauca.archsoftmeasure.question.model.Question;
import co.unicauca.archsoftmeasure.scale.model.Scale;
import co.unicauca.archsoftmeasure.scale.repository.IScaleRepository;
import co.unicauca.archsoftmeasure.test.dominio.request.TestRequestDTO;
import co.unicauca.archsoftmeasure.test.dominio.response.TestResponseDTO;
import co.unicauca.archsoftmeasure.test.dominio.response.TestSimpleResponseDTO;
import co.unicauca.archsoftmeasure.test.model.Test;
import co.unicauca.archsoftmeasure.test.repository.ITestRepository;
import co.unicauca.archsoftmeasure.util.exception.ServiceRuleException;
import co.unicauca.archsoftmeasure.util.response.Response;
import co.unicauca.archsoftmeasure.util.response.handler.ResponseHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TestService implements ITestService {
    private final ITestRepository iTestRepository;
    private final IScaleRepository iScaleRepository;
    private final IMetricRepository iMetricRepository;
    private final ModelMapper modelMapper;
    private final ObjectMapper objectMapper;


    public TestService(ITestRepository iTestRepository, IScaleRepository iScaleRepository, IMetricRepository iMetricRepository, ModelMapper modelMapper, ObjectMapper objectMapper) {
        this.iTestRepository = iTestRepository;
        this.iScaleRepository = iScaleRepository;
        this.iMetricRepository = iMetricRepository;
        this.modelMapper = modelMapper;
        this.objectMapper = objectMapper;
    }

    @Override
    public Response<Boolean> createTest(TestRequestDTO testRequestDTO) {
        Test test = new Test();
        test.setName(testRequestDTO.getName());
        if(testRequestDTO.getQuestions() != null) {
            List<Question> questions = new ArrayList<>();
            for(int i = 0; i < testRequestDTO.getQuestions().size(); i++) {
                QuestionRequestDTO questionRequestDTO = testRequestDTO.getQuestions().get(i);
                Question question = new Question();
                question.setStatement(questionRequestDTO.getStatement());
                if(questionRequestDTO.getAnswers() != null) {
                    List<Answer> answers = new ArrayList<>();
                    for(int j = 0; j < questionRequestDTO.getAnswers().size(); j++) {
                        Answer answer = new Answer();
                        answer.setStatement(questionRequestDTO.getAnswers().get(j).getStatement());
                        answer.setQuestion(question);
                        List<Double> scales = questionRequestDTO.getAnswers().get(j).getMetricScaleValues();
                        if(scales.size() != questionRequestDTO.getMetrics().size()) {
                            throw new ServiceRuleException("scales.size.is.not.equals.to.metrics.size");
                        }
                        List<Scale> scaleList = new ArrayList<>();
                        for(int k=0;k<scales.size();k++) {
                            Optional<Metric> metricOptional = this.iMetricRepository.findById(questionRequestDTO.getMetrics().get(k));
                            if (metricOptional.isEmpty()) {
                                throw new ServiceRuleException("metric.is.not.found");
                            }
                            Optional<Scale> scaleOptional = iScaleRepository.findByPercentageAndMetric(scales.get(k), metricOptional.get());
                            if(scaleOptional.isPresent()) {
                                scaleList.add(scaleOptional.get());
                            } else {
                                Scale scale = new Scale();
                                scale.setPercentage(scales.get(k));
                                scale.setMetric(metricOptional.get());
                                this.iScaleRepository.save(scale);
                                scaleList.add(scale);
                            }
                        }
                        answer.setScales(scaleList);
                        answers.add(answer);

                    }
                    question.setAnswers(answers);
                } else {
                    question.setAnswers(new ArrayList<>());
                }
                questions.add(question);
            }
            test.setQuestions(questions);
        } else {
            test.setQuestions(new ArrayList<>());
        }

        iTestRepository.save(test);

        return new ResponseHandler<>(200,"Examen creado con éxito.","http://localhost:8080/test/createTest",Boolean.TRUE).getResponse();
    }

    @Override
    public Response<TestResponseDTO> getTest(Integer id) {
        Optional<Test> testOptional = iTestRepository.findById(id);
        if(testOptional.isEmpty()) {
            throw new ServiceRuleException("test.is.not.found");
        }
        Test test = testOptional.get();
        TestResponseDTO testResponseDTO = modelMapper.map(test, TestResponseDTO.class);
        List<QuestionResponseDTO> questionResponseDTOList = new ArrayList<>();
        for(Question question : test.getQuestions()) {
            QuestionResponseDTO questionResponseDTO = modelMapper.map(question, QuestionResponseDTO.class);
            List<AnswerResponseDTO> answerResponseDTOList = new ArrayList<>();
            for(Answer answer : question.getAnswers()) {
                answerResponseDTOList.add(modelMapper.map(answer, AnswerResponseDTO.class));
            }
            questionResponseDTO.setAnswers(answerResponseDTOList);
            questionResponseDTOList.add(questionResponseDTO);
        }
        testResponseDTO.setQuestions(questionResponseDTOList);

        return new ResponseHandler<>(200,"Examen encontrado con éxito.","http://localhost:8080/test/getTest/{id}",testResponseDTO).getResponse();
    }

    @Override
    public Response<List<TestSimpleResponseDTO>> getAllTests() {
        List<Test> tests = iTestRepository.findAll();
        List<TestSimpleResponseDTO> testResponseDTOList = tests.stream().map(test -> modelMapper.map(test, TestSimpleResponseDTO.class)).toList();

        return new ResponseHandler<>(200,"Se han encontrado los test con éxito.","http://localhost:8080/test/getAllTests",testResponseDTOList).getResponse();
    }
}
