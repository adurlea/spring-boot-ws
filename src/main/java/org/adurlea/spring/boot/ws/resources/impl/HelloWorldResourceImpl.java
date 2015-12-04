package org.adurlea.spring.boot.ws.resources.impl;

import javax.ws.rs.core.Response;

import org.adurlea.spring.boot.ws.resources.HelloWorldResource;
import org.springframework.stereotype.Component;

/**
 * Created by adurlea on 02/12/15.
 */
@Component
public class HelloWorldResourceImpl implements HelloWorldResource {

    @Override
    public Response getHelloWorld() {
        return Response.ok().entity("{\"Hello\":\"World\"}").build();
    }
}
