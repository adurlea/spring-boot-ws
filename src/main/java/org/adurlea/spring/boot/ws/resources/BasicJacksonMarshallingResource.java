package org.adurlea.spring.boot.ws.resources;


import org.adurlea.spring.boot.ws.entities.JacksonInjectBean;
import org.adurlea.spring.boot.ws.entities.JsonCreatorBean;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Created by adurlea on 23/11/18.
 */
@Path(BasicJacksonMarshallingResource.BASIC_JACKSON_MARSHALLING_PATH)
@Produces({ APPLICATION_JSON + ";charset=utf-8" })
@Consumes({ APPLICATION_JSON + ";charset=utf-8" })
public interface BasicJacksonMarshallingResource {
    String BASIC_JACKSON_MARSHALLING_PATH = "basicJacksonMarshalling";
    String REQUEST_ID = "requestId";

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

    @POST
    @Path("/jacksonInject/{" + REQUEST_ID + "}")
    Response postJacksonInject(String json, @PathParam(REQUEST_ID) Integer id);
}
