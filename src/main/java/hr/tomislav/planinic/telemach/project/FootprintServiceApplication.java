package hr.tomislav.planinic.telemach.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Main application class that starts the Spring Boot application.
 */
@SpringBootApplication
@EnableJpaAuditing
@SpringBootConfiguration
public class FootprintServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FootprintServiceApplication.class, args);
    }
}
