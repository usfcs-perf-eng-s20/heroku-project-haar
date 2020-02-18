package usfca.edu;

import java.sql.Timestamp;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
import usfca.edu.db.model.Edr;
import usfca.edu.persistence.EdrRepository;

@SpringBootApplication
@EnableSwagger2
public class AnalyticsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnalyticsApplication.class, args);
    }

    @Bean
    ApplicationRunner applicationRunner(EdrRepository greetingRepository) {
        return args -> {
            greetingRepository.save(new Edr(new Timestamp(System.currentTimeMillis()),
                                            "Search MS",
                                            120,
                                            "Test-1"));

            greetingRepository.save(new Edr(new Timestamp(System.currentTimeMillis()),
                                            "Login MS",
                                            80,
                                            "Test-2"));

        };
    }
}
