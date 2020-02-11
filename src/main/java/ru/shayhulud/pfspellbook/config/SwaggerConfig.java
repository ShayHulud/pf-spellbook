package ru.shayhulud.pfspellbook.config;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Swagger configuration for API.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Value("${project.version}")
	private String projectVersion;

	@Bean
	public Docket productsApiForDev() {
		return constructDocket();
	}

	public Docket constructDocket() {
		List<ResponseMessage> commonApiErrors = Lists.newArrayList(
			new ResponseMessageBuilder().code(400).message("Bad Request").build(),
			new ResponseMessageBuilder().code(500).message("Internal Server Error").build()
		);

		return new Docket(DocumentationType.SWAGGER_2)
			.select()
			.apis(RequestHandlerSelectors.basePackage("ru.shayhulud.pfspellbook.controller"))
			.paths(PathSelectors.any())
			.build()
			.apiInfo(apiInfo())
			.useDefaultResponseMessages(false)
			.globalResponseMessage(RequestMethod.GET, commonApiErrors)
			.globalResponseMessage(RequestMethod.POST, commonApiErrors)
			.globalResponseMessage(RequestMethod.PUT, commonApiErrors)
			.globalResponseMessage(RequestMethod.DELETE, commonApiErrors)
			.globalResponseMessage(RequestMethod.HEAD, commonApiErrors);
	}

	private String readDescriptionFromFile() {
		BufferedReader reader = new BufferedReader(
			new InputStreamReader(
				getClass().getResourceAsStream("/swaggerDescription.md")
			)
		);
		return reader.lines().collect(Collectors.joining(System.lineSeparator()));
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
			.title("PF-SPELLBOOK API")
			.description(readDescriptionFromFile())
			.version(this.projectVersion)
			.build();
	}
}
