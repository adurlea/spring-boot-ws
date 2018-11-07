package org.adurlea.spring.boot.ws.conf;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static com.fasterxml.jackson.databind.DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.SerializationFeature.FAIL_ON_EMPTY_BEANS;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_EMPTY_JSON_ARRAYS;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_NULL_MAP_VALUES;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.mrbean.MrBeanModule;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.ErrorPageFilter;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by adurlea on 01/12/15.
 */
@Configuration
@ComponentScan(basePackages = { "org.adurlea.spring.boot.ws" })
@EnableAutoConfiguration
public class SpringBootWsApplication extends SpringBootServletInitializer {

    @Bean
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper()
                .disable(WRITE_EMPTY_JSON_ARRAYS)
                .disable(WRITE_NULL_MAP_VALUES)
                .disable(FAIL_ON_EMPTY_BEANS)
                .disable(WRITE_DATES_AS_TIMESTAMPS)
                .disable(FAIL_ON_UNKNOWN_PROPERTIES)
                .enable(ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)
                .registerModule(new MrBeanModule())
                .setSerializationInclusion(NON_NULL);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringBootWsApplication.class)
                          .bannerMode(Banner.Mode.CONSOLE)
                          .logStartupInfo(true);
    }

    @Override
    protected WebApplicationContext run(SpringApplication application) {
        application.getSources().remove(ErrorPageFilter.class);
        return super.run(application);
    }

    public static void main(String[] args) {
        new SpringBootWsApplication().configure(new SpringApplicationBuilder(SpringBootWsApplication.class))
                                     .run(args);
    }
}
