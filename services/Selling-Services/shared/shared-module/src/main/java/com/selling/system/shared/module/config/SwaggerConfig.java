package com.selling.system.shared.module.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.parameters.HeaderParameter;
import io.swagger.v3.oas.models.parameters.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.stereotype.Component;


@Component
@Setter
@RequiredArgsConstructor
public class SwaggerConfig implements OpenApiCustomizer {

    private final AppConfig appConfig;

    @Override
    public void customise(OpenAPI openApi) {
        Parameter acceptLangHeader = new HeaderParameter().name("Accept-Language")
                .description("The Language of The Locale")
                .required(false);
        appConfig.getLanguages().forEach($ -> acceptLangHeader.addExample($, new Example().value($)));
        openApi.getPaths().values().forEach(pathItem -> {
            pathItem.readOperations().forEach(operation -> {
                operation.addParametersItem(acceptLangHeader);
            });
        });
    }

}
