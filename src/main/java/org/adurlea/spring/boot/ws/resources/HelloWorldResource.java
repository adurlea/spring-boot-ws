package org.adurlea.spring.boot.ws.resources;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * Created by adurlea on 02/12/15.
 */
@Path("helloWorld")
@Produces({ APPLICATION_JSON + ";charset=utf-8" })
public interface HelloWorldResource {

    @GET
    Response getHelloWorld();
}
