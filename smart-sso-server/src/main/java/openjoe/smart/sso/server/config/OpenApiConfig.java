package openjoe.smart.sso.server.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Smart SSO Server API",
                version = "v1",
                description = "REST APIs for Smart SSO (token, refresh, admin)"
        )
)
public class OpenApiConfig {
}

