package um.edu.bagues.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {
    
    @Bean
    public OpenAPI baguesOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Bagues API")
                .description("API to manage the business logic of the company Bagues, a leader in the sale of beauty and personal care products.")
                .version("v1.0.0")
                .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}
