package br.com.menesic.GranjaApp.application.config.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                description = "Serviço responsável pela gestão de uma granja de patos",
                version = "1.0.0",
                title = "Granja de Patos App",
                contact = @Contact(
                        name = "Menesic",
                        url = "https://www.linkedin.com/in/gabriel-meneses-da-silva-nascimento-49a18118a/",
                        email = "gmsn@icomp.ufam.edu.br"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "http://www.apache.org/licenses/LICENSE-2.0.html"
                )
        )
)
public class SwaggerConfig {
}
