package com.linkedin.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.OAuth;
import springfox.documentation.service.ResourceOwnerPasswordCredentialsGrant;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Value("${linkedin.oauth.clientId}")
	String clientId;
	@Value("${linkedin.oauth.clientSecret}")
	String clientSecret;

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.linkedin.controller"))
				.paths(PathSelectors.any())
				.build()
				.securitySchemes(Collections.singletonList(securityScheme()))
				.securityContexts(Collections.singletonList(securityContext()))
				.consumes(new HashSet<>(Collections.singletonList(MediaType.APPLICATION_JSON_VALUE)))
				.produces(new HashSet<>(Collections.singletonList(MediaType.APPLICATION_JSON_VALUE)));
	}

	@Bean
	public SecurityConfiguration security() {
		return SecurityConfigurationBuilder.builder()
				.clientId(clientId)
				.clientSecret(clientSecret)
				.scopeSeparator(" ")
				.build();
	}


	private SecurityScheme securityScheme() {

		List<AuthorizationScope> authorizationScopeList = new ArrayList<>();
		authorizationScopeList.add(new AuthorizationScope("read", "for read operations"));
		authorizationScopeList.add(new AuthorizationScope("write", "for write operations"));

		List<GrantType> grantTypes = new ArrayList<>();
		GrantType creGrant = new ResourceOwnerPasswordCredentialsGrant("http://localhost:8080/oauth/token");

		grantTypes.add(creGrant);

		return new OAuth("oauth2schema", authorizationScopeList, grantTypes);
	}


	private SecurityContext securityContext() {
		return SecurityContext.builder()
				.securityReferences(defaultAuth())
				.forPaths(PathSelectors.ant("/users/**"))
				.forPaths(PathSelectors.ant("/api/**"))
				.build();
	}


	private List<SecurityReference> defaultAuth() {

		AuthorizationScope[] scopes = {
				new AuthorizationScope("read", "for read operations"),
				new AuthorizationScope("write", "for write operations")};

		return Collections.singletonList(new SecurityReference("oauth2schema", scopes));
	}
}
