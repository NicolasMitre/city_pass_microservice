package net.avalith.city_pass.config;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.or;


@Configuration
@EnableSwagger2
public class SpringFoxConfig {
    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("CityPass")
                .description("Best CityPass App")
                .license("Apache 2.0")
                .licenseUrl("http://localhost:8080/api/docs")
                .termsOfServiceUrl("")
                .version("1.0.0")
                .contact(new Contact("", "", "contact@contact.com.uy"))
                .build();
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("net.avalith"))
                .paths(apiPaths())
                .build()
                .apiInfo(apiInfo());
    }

    private Predicate<String> apiPaths() {
        return or(PathSelectors.regex("/cities"),
                PathSelectors.regex("/cities/.*"),
                PathSelectors.regex("/city_pass"),
                PathSelectors.regex("/city_pass/.*"),
                PathSelectors.regex("/excursion"),
                PathSelectors.regex("/excursion/.*"),
                PathSelectors.regex("/purchases"),
                PathSelectors.regex("/purchases/.*"),
                PathSelectors.regex("/role"),
                PathSelectors.regex("/role/.*"),
                PathSelectors.regex("/theaterplay"),
                PathSelectors.regex("/theaterplay/.*"),
                PathSelectors.regex("/users"),
                PathSelectors.regex("/users/.*"));

    }

}