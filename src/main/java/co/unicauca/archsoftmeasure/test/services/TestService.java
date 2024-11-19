package co.unicauca.archsoftmeasure.test.services;

import co.unicauca.archsoftmeasure.answer.dominio.request.AnswerRequestDTO;
import co.unicauca.archsoftmeasure.answer.model.Answer;
import co.unicauca.archsoftmeasure.question.dominio.request.QuestionRequestDTO;
import co.unicauca.archsoftmeasure.question.model.Question;
import co.unicauca.archsoftmeasure.test.dominio.request.TestRequestDTO;
import co.unicauca.archsoftmeasure.test.model.Test;
import co.unicauca.archsoftmeasure.test.repository.ITestRepository;
import co.unicauca.archsoftmeasure.util.response.Response;
import co.unicauca.archsoftmeasure.util.response.handler.ResponseHandler;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestService implements ITestService {
    private final ITestRepository iTestRepository;
    private final ModelMapper modelMapper;


    public TestService(ITestRepository iTestRepository, ModelMapper modelMapper) {
        this.iTestRepository = iTestRepository;
        this.modelMapper = modelMapper;
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
}
