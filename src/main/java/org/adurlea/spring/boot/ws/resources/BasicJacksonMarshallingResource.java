package org.adurlea.spring.boot.ws.resources;

import com.fasterxml.jackson.core.JsonProcessingException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Created by adurlea on 23/11/18.
 */
@Path("basicJacksonMarshalling")
@Produces({ APPLICATION_JSON + ";charset=utf-8" })
public interface BasicJacksonMarshallingResource {

    @GET
    @Path("/jsonAnyGetter")
    Response getJsonAnyGetter();

    @GET
    @Path("/jsonGetter")
    Response getJsonGetter();

    @GET
    @Path("/jsonPropertyOrder")
    Response getJsonPropertyOrder();

    @GET
    @Path("/jsonRawValue")
    Response getJsonRawValue();

    @GET
    @Path("/jsonValue")
    Response getJsonValue();

    @GET
    @Path("/jsonRootName")
    Response getJsonRootName() throws JsonProcessingException;
}
