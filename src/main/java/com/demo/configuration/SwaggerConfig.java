package com.demo.configuration;

import static org.apache.commons.lang3.StringUtils.EMPTY;

import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger configuration for easy API development.
 */
@Configuration
@EnableWebMvc
@EnableSwagger2
@NoArgsConstructor
public class SwaggerConfig extends WebMvcConfigurerAdapter {

  /**
   * Get a Docket instance object for swagger.
   * @return a Docket object
   */
  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.demo.controller"))
        .paths(PathSelectors.ant("/v1/messages/*"))
        .build()
        .apiInfo(apiInfo());
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("swagger-ui.html")
        .addResourceLocations("classpath:/META-INF/resources/");

    registry.addResourceHandler("/webjars/**")
        .addResourceLocations("classpath:/META-INF/resources/webjars/");
  }

  /**
   * Get data from this API.
   * @return an {@link ApiInfo} object
   */
  private ApiInfo apiInfo() {
    ApiInfo apiInfo = new ApiInfo(
        "Message REST API",
        "Demo Message REST API using Spring.",
        "V1",
        EMPTY,
        ApiInfo.DEFAULT_CONTACT,
        EMPTY,
        EMPTY);
    return apiInfo;
  }
}
