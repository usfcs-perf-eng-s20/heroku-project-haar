package usfca.edu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import usfca.edu.model.Edr;
import usfca.edu.persistence.EdrRepository;

import java.sql.Timestamp;

@RestController
public class AnalyticsController {

    private final EdrRepository edrRepository;

    public AnalyticsController(EdrRepository edrRepository) {
        this.edrRepository = edrRepository;
    }

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
        edrRepository.save(new Edr(new Timestamp(System.currentTimeMillis()),
                "Search MS",
                120,
                "Test-1"));
        return "This API will return number of API calls for selected service!";
    }
}
