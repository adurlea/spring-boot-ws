package org.adurlea.spring.boot.ws.resources.impl;

import org.adurlea.spring.boot.ws.entities.JsonAnyGetterBean;
import org.adurlea.spring.boot.ws.entities.JsonGetterBean;
import org.adurlea.spring.boot.ws.entities.JsonPropertyOrderBean;
import org.adurlea.spring.boot.ws.resources.BasicJacksonMarshallingResource;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by adurlea on 23/11/18.
 */
@Component
public class BasicJacksonMarshallingResourceImpl implements BasicJacksonMarshallingResource {

    @Override
    public Response getJsonAnyGetter() {
        JsonAnyGetterBean bean = new JsonAnyGetterBean();
        bean.setName("@JsonAnyGetter: When used the elements of a Map will be shown as single json plain properties " +
                "and no as a collection of json plain properties! " +
                "See difference between propertiesJsonAnyGetter = [(JsonAnyGetter1, JsonAnyGetterVal1)," +
                "(JsonAnyGetter2, JsonAnyGetterVal2)] " +
                "and propertiesNoJsonAnyGetter = [(JsonAnyGetter1No, JsonAnyGetterVal1No)," +
                "(JsonAnyGetter2No, JsonAnyGetterVal2No)] ");
        Map<String, String> properties = new HashMap<>();
        properties.put("JsonAnyGetter1", "JsonAnyGetterVal1");
        properties.put("JsonAnyGetter2", "JsonAnyGetterVal2");
        bean.setProperties(properties);

        Map<String, String> propertiesNo = new HashMap<>();
        propertiesNo.put("JsonAnyGetter1No", "JsonAnyGetterVal1No");
        propertiesNo.put("JsonAnyGetter2No", "JsonAnyGetterVal2No");
        bean.setPropertiesNoJsonAnyGetter(propertiesNo);

        return Response.ok().entity(bean).build();
    }

    @Override
    public Response getJsonGetter() {
        JsonGetterBean bean = new JsonGetterBean();
        bean.setId(1);
        bean.setNameJsonGetter("Contains @JsonGetter on the theNameJsonGetter method and will be shown as response");
        bean.setNameNoJsonGetter(
                "Doesn't contains @JsonGetter on the theNameNoJsonGetter method and will not be shown as response");

        return Response.ok().entity(bean).build();
    }

    @Override
    public Response getJsonPropertyOrder() {
        JsonPropertyOrderBean bean = new JsonPropertyOrderBean(1,
                "Using @JsonPropertyOrder will show this as first element on the result " +
                        "even if it is declared as 2nd element");
        return Response.ok().entity(bean).build();
    }
}
