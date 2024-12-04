package co.unicauca.archsoftmeasure.test.controller;

import co.unicauca.archsoftmeasure.test.dominio.request.TestRequestDTO;
import co.unicauca.archsoftmeasure.test.dominio.response.TestResponseDTO;
import co.unicauca.archsoftmeasure.test.dominio.response.TestSimpleResponseDTO;
import co.unicauca.archsoftmeasure.test.services.ITestService;
import co.unicauca.archsoftmeasure.util.response.Response;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TestController {
    private final ITestService iTestService;

    public TestController(ITestService iTestService) {
        this.iTestService = iTestService;
    }

    @PostMapping("/createTest")
    public Response<Boolean> createTest(@RequestBody final TestRequestDTO testRequestDTO) {
        return iTestService.createTest(testRequestDTO);
    }

    @GetMapping("/getTest/{id}")
    public Response<TestResponseDTO> getTest(@PathVariable final Integer id) {
        return iTestService.getTest(id);
    }

    @GetMapping("/getAllTests")
    public Response<List<TestSimpleResponseDTO>> getAllTests() {
        return iTestService.getAllTests();
    }
}
