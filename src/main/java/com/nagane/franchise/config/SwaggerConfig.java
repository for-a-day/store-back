package com.nagane.franchise.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.RequiredArgsConstructor;
 
@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {
	
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(apiInfo());
    }
 
    private Info apiInfo() {
        return new Info()
                .title("Springdoc 테스트")
                .description("Springdoc을 사용한 Swagger UI 테스트")
                .version("1.0.0");
    }
    
    @Bean
    public GroupedOpenApi api() {
    	String[] paths = {"/**"};
    	return GroupedOpenApi
    			.builder()
    			.group("springdoc-openapi")
    			.pathsToMatch(paths)
    			.build();
    	
    }
}