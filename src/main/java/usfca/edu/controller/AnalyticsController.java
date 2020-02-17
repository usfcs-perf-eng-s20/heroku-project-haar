package usfca.edu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnalyticsController {
    @GetMapping("/getAllAPICalls")
    String getAllAPICalls() {
        return "This API will return number of API calls for each service!";
    }

    @GetMapping("/getResponseTıme")
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
        return "This API will be used to send data from other MSs to Analytics MS!";
    }
}
