package usfca.edu.controller;

import java.sql.Timestamp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import usfca.edu.model.Edr;
import usfca.edu.persistence.EdrRepository;

@RestController
public class HelloController {

    private final EdrRepository edrRepository;

    public HelloController(EdrRepository edrRepository) {
        this.edrRepository = edrRepository;
    }

    @GetMapping("/helloworld")
    String hello() {
        return "Hello World-2";
    }

    @GetMapping("/welcome")
    Iterable<Edr> edrs() {
        return edrRepository.findAll();
    }

    @PostMapping("/saveEdr")
    Iterable<Edr> saveEdrs() {

        Edr edr = new Edr(new Timestamp(System.currentTimeMillis()),
                          "search",
                          System.currentTimeMillis(),
                          "Test");

        return edrRepository.save(edr);
    }

}
