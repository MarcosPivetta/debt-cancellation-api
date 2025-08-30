package com.example.debtcancellation.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI debtCancellationOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl("http://localhost:8080");
        devServer.setDescription("Server URL in Development environment");

        Contact contact = new Contact();
        contact.setEmail("marcospivetta.ti@gmail.com");
        contact.setName("Marcos Pivetta");

        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("Debt Cancellation API")
                .version("1.0")
                .contact(contact)
                .description("API para controle de cancelamentos de débitos dos clientes. " +
                           "Esta API permite solicitar o cancelamento de débitos através do ID, " +
                           "aplicando validações de negócio e políticas de cancelamento.")
                .termsOfService("https://www.example.com/terms")
                .license(mitLicense);

        return new OpenAPI().info(info).servers(List.of(devServer));
    }
}
