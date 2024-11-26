package co.unicauca.archsoftmeasure.test.services;

import co.unicauca.archsoftmeasure.test.dominio.request.TestRequestDTO;
import co.unicauca.archsoftmeasure.test.dominio.response.TestResponseDTO;
import co.unicauca.archsoftmeasure.util.response.Response;

public interface ITestService {
    public Response<Boolean> createTest(TestRequestDTO testRequestDTO);
    public Response<TestResponseDTO> getTest(Integer id);
}
