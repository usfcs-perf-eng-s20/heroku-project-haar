package usfca.edu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import usfca.edu.db.model.Edr;
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

    //    @PostMapping("/saveEdr")
    //    void saveEdrs() {
    //
    //        Edr edr = new Edr(new Timestamp(System.currentTimeMillis()),
    //                          "search",
    //                          System.currentTimeMillis(),
    //                          "Test");
    //
    //        edrRepository.save(edr);
    //    }

}
