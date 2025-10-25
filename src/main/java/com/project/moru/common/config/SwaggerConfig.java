package com.project.moru.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Value("${server.servlet.context-path:/}")
    private String contextPath;

    @Value("${server.port:8080}")
    private String serverPort;

    @Bean
    public OpenAPI openAPI() {
        Server server = new Server();
        server.setUrl("http://localhost:" + serverPort + contextPath);
        server.setDescription("로컬 개발 서버");

        Contact contact = new Contact();
        contact.setName("Moru Team");
        contact.setEmail("moru@example.com");

        Info info = new Info()
                .title("Moru API Documentation")
                .version("v1.0.0")
                .description("Moru 프로젝트의 REST API 문서입니다. 이 문서를 통해 API를 테스트하고 이해할 수 있습니다.")
                .contact(contact);

        return new OpenAPI()
                .info(info)
                .servers(List.of(server));
    }
}

