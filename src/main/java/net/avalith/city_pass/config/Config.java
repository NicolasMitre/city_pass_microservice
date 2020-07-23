package net.avalith.city_pass.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.time.Duration;

@Configuration
@PropertySource("application.yml")
@EnableScheduling
public class Config {
    @Bean
    public RestTemplate getRestTemplate() {
        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
        return new RestTemplateBuilder()
                .setConnectTimeout(Duration.ofSeconds(60))
                .additionalMessageConverters(stringHttpMessageConverter)
                .build();
    }
}