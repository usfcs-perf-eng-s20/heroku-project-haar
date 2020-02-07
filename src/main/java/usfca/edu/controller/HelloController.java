package usfca.edu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import usfca.edu.model.Edr;
import usfca.edu.persistence.EdrRepository;

@RestController
public class HelloController {

    private final EdrRepository edrRepository;

    public HelloController(EdrRepository edrRepository) {
        this.edrRepository = edrRepository;
    }

    @GetMapping("/")
    String hello() {
        return "Hello World";
    }

    @GetMapping("/welcome")
    Iterable<Edr> edrs() {
        return edrRepository.findAll();
    }

}
