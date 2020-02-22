package usfca.edu.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import usfca.edu.db.service.EdrService;
import usfca.edu.json.model.EdrForm;
import usfca.edu.persistence.EdrRepository;

@RestController
public class AnalyticsController {

    private final EdrRepository edrRepository;
    private final EdrService    edrService;

    public AnalyticsController(EdrRepository edrRepository, EdrService edrService) {
        this.edrRepository = edrRepository;
        this.edrService = edrService;
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

    @ApiOperation(value = "This API will save EDR to database!", response = String.class)
    @PostMapping("/saveEdr")
    @ResponseStatus(value = HttpStatus.OK)
    String saveEdr(@RequestBody EdrForm edrForm) {

        System.out.println("test.saveEdr....");

        if (edrService.saveEdr(edrForm)) {
            return "Edr saved successfully!!!!!!!!!!!!";
        } else {
            return "Edr could not be saved!!!!!!!!!!!!";
        }

    }
}
