package tn.esprit.spring.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.ByteArraySchema;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;


@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(infoAPI())
                .components(new Components().addSchemas("MultipartFile", new MultipartFileSchema()));
    }
    public Info infoAPI() {
        return new Info().title("ESPRIT Mobility")
                .contact(contactAPI());
    }
    public Contact contactAPI() {
        Contact contact = new Contact().name("Equipe ASI II")
                .email("departement.mobility@gmail.com")
                .url("https://www.linkedin.com/in/**********/");
        return contact;
    }

    private static class MultipartFileSchema extends ByteArraySchema {
        public MultipartFileSchema() {
            super();
            this.format("binary");
        }
    }




}

