package org.yunzhong.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author yunzhong
 *
 */
@Configuration
@EnableSwagger2
@ComponentScan("org.yunzhong")
public class ApplicationConfig {

	/**
	 * @return
	 */
	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("org.yunzhong.controller")).paths(PathSelectors.any())
				.build();
	}

	/**
	 * @return
	 */
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("History data").description("recommend history").version("1.0").build();
	}
}
