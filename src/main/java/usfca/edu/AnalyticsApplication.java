package usfca.edu;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
import usfca.edu.persistence.EdrRepository;

@SpringBootApplication
@EnableSwagger2
@EnableJpaRepositories(basePackages="usfca.edu")
@EntityScan(basePackages="usfca.edu")
public class AnalyticsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnalyticsApplication.class, args);
    }

    @Bean
    ApplicationRunner applicationRunner(EdrRepository greetingRepository) {
        return args -> {
            //            greetingRepository.save(new Edr(new Timestamp(System.currentTimeMillis()),
            //                                            "Search MS",
            //                                            120,
            //                                            "Test-1"));
            //
            //            greetingRepository.save(new Edr(new Timestamp(System.currentTimeMillis()),
            //                                            "Login MS",
            //                                            80,
            //                                            "Test-2"));

        };
    }
}
