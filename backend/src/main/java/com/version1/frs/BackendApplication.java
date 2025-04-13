package com.version1.frs;
 
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
 
@SpringBootApplication
@EntityScan(basePackages = "com.version1.frs.model")                    // ensures model classes are scanned
@EnableJpaRepositories(basePackages = "com.version1.frs.repository")   // ensures repo interfaces are scanned
public class BackendApplication {
 
    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }
}