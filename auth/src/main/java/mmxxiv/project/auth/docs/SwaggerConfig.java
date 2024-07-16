package mmxxiv.project.auth.docs;

import mmxxiv.project.core.docs.BaseSwaggerConfig;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {
    public SwaggerConfig() {
        super("mmxxiv.project.auth.endpoint.controller");
    }
}
