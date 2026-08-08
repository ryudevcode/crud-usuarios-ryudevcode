package crud_usuarios_ryudevcode.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Configuración de Swagger/OpenAPI
@Configuration
public class OpenApiConfig {

    // Configura la información general de nuestra API
    @Bean
    public OpenAPI usuarioOpenAPI() {

        return new OpenAPI()
                .info(new Info()

                        // Nombre de la API
                        .title("CRUD Usuarios - RyudevCode")

                        // Versión de la API
                        .version("1.0.0")

                        // Descripción general
                        .description(
                                "API REST para la gestión de usuarios. " +
                                        "Proyecto desarrollado con Spring Boot, " +
                                        "Spring Data JPA, Hibernate y H2."
                        )

                        // Información del proyecto
                        .contact(
                                new Contact()
                                        .name("RyudevCode")
                        )
                );
    }

}