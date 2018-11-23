package org.adurlea.spring.boot.ws.conf;

import static org.glassfish.jersey.server.ServerProperties.MONITORING_STATISTICS_MBEANS_ENABLED;

import org.adurlea.spring.boot.ws.providers.ObjectMapperResolver;
import org.adurlea.spring.boot.ws.resources.BasicJacksonMarshallingResource;
import org.adurlea.spring.boot.ws.resources.HelloWorldResource;
import org.adurlea.spring.boot.ws.resources.impl.BasicJacksonMarshallingResourceImpl;
import org.adurlea.spring.boot.ws.resources.impl.HelloWorldResourceImpl;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

/**
 * Created by adurlea on 01/12/15.
 */
@Configuration
public class JerseyConfig extends ResourceConfig{
    private static final String APPLICATION_NAME = "spring-boot-ws";

    public JerseyConfig() {
        setApplicationName(APPLICATION_NAME);
        packages("org.adurlea.spring.boot.ws.resources");
        register(ObjectMapperResolver.class);
        register(JacksonFeature.class);
        register(new AbstractBinder() {
            @Override
            protected void configure() {
                // This is to tell Jersey how to find the concrete classes of the resource interfaces
                bind(HelloWorldResourceImpl.class).to(HelloWorldResource.class);
                bind(BasicJacksonMarshallingResourceImpl.class).to(BasicJacksonMarshallingResource.class);
            }
        });

        property(MONITORING_STATISTICS_MBEANS_ENABLED, true);
    }
}
