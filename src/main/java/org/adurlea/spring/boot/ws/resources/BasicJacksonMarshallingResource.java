package org.adurlea.spring.boot.ws.resources;


import org.adurlea.spring.boot.ws.entities.JsonCreatorBean;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Created by adurlea on 23/11/18.
 */
@Path("basicJacksonMarshalling")
@Produces({ APPLICATION_JSON + ";charset=utf-8" })
@Consumes({ APPLICATION_JSON + ";charset=utf-8" })
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
    Response getJsonRootName();

    @GET
    @Path("/jsonSerialize")
    Response getJsonSerialize();

    @POST
    @Path("/jsonCreator")
    Response postJsonCreator(JsonCreatorBean bean);
}
