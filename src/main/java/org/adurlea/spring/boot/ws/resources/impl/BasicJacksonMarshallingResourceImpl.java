package org.adurlea.spring.boot.ws.resources.impl;

import org.adurlea.spring.boot.ws.entities.BasicJacksonMarshallingBean;
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
        BasicJacksonMarshallingBean bean = new BasicJacksonMarshallingBean();
        bean.setName("@JsonAnyGetter: When used the elements of a Map will be shown as single json plain properties " +
                "and no as a collection of json plain properties! " +
                "See difference between propertiesJsonAnyGetter = [(attr1, val1),(attr2, val2)] " +
                "and propertiesNoJsonAnyGetter = [(attr1No, val1No),(attr2No, val2No)] ");
        Map<String, String> properties = new HashMap<>();
        properties.put("attr1", "val1");
        properties.put("attr2", "val2");
        bean.setProperties(properties);

        Map<String, String> propertiesNo = new HashMap<>();
        propertiesNo.put("attr1No", "val1No");
        propertiesNo.put("attr2No", "val2No");
        bean.setPropertiesNoJsonAnyGetter(propertiesNo);

        return Response.ok().entity(bean).build();
    }
}
