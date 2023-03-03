package tn.esprit.spring;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ExamenBlancTemplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExamenBlancTemplateApplication.class, args);
	}
	@Bean
	public Jackson2ObjectMapperBuilderCustomizer addJavaTimeModule() {
		return builder -> builder.modulesToInstall(new JavaTimeModule());
	}

}
