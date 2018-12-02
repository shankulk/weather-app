package com.shankulk.weatherapp.config;

import static com.shankulk.weatherapp.util.WeatherConstants.BASE_PACKAGE;
import static com.shankulk.weatherapp.util.WeatherUtil.getApiInfo;
import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class WeatherConfiguration {
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder
				.build();
	}

	@Bean
	public Docket swaggerApi() {
		return new Docket(SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(getApiInfo());
	}
}
