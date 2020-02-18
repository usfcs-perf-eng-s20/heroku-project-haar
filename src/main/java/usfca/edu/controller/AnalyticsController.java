package usfca.edu.controller;

import java.sql.Timestamp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import usfca.edu.model.Edr;
import usfca.edu.persistence.EdrRepository;

@RestController
public class AnalyticsController {

    private final EdrRepository edrRepository;

    public AnalyticsController(EdrRepository edrRepository) {
        this.edrRepository = edrRepository;
    }

    @ApiOperation(value = "This API will return number of API calls for each service!",
            response = String.class)
    @ApiResponses(
            value = {@ApiResponse(code = 200, message = "Test getAllAPICalls!"),
                     @ApiResponse(code = 401,
                             message = "You are not authorized to view the resource"),
                     @ApiResponse(code = 403,
                             message = "Accessing the resource you were trying to reach is forbidden"),
                     @ApiResponse(code = 404,
                             message = "The resource you were trying to reach is not found")

            })
    @GetMapping("/getAllAPICalls")
    String getAllAPICalls() {
        return "[Update]This API will return number of API calls for each service2!";
    }

    @ApiOperation(value = "This API will return average response time for selected service!",
            response = String.class)
    @ApiResponses(
            value = {@ApiResponse(code = 200, message = "Test getAllAPICalls!"),
                     @ApiResponse(code = 401,
                             message = "You are not authorized to view the resource"),
                     @ApiResponse(code = 403,
                             message = "Accessing the resource you were trying to reach is forbidden"),
                     @ApiResponse(code = 404,
                             message = "The resource you were trying to reach is not found")

            })
    @GetMapping("/getResponseTime")
    String getResponseTıme() {
        return "This API will return average response time for selected service!";
    }

    @GetMapping("/getNumberOfError")
    String getNumberOfError() {
        return "This API will return number of errors for selected service!";
    }

    @GetMapping("/getNumberOfAPICall")
    String getNumberOfAPICall() {
        return "This API will return number of API calls for selected service!";
    }

    @PostMapping("/saveEdr")
    String saveEdr() {
        edrRepository.save(new Edr(new Timestamp(System.currentTimeMillis()),
                                   "Search MS",
                                   120,
                                   "Test-1"));
        return "This API will return number of API calls for selected service!";
    }
}
