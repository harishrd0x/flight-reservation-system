package com.version1.frs;
 
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
 
@SpringBootApplication
@EntityScan(basePackages = "com.version1.frs.model")                    // ensures model classes are scanned
@EnableJpaRepositories(basePackages = "com.version1.frs.repository")   // ensures repo interfaces are scanned
public class BackendApplication {
 
    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }
 
    // Global CORS configuration
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // allow all endpoints
                        .allowedOrigins("*") // allow all origins (frontend apps)
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*");
            }
        };
    }
}