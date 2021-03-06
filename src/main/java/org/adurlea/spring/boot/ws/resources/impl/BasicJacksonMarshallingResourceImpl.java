package org.adurlea.spring.boot.ws.resources.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.adurlea.spring.boot.ws.entities.*;
import org.adurlea.spring.boot.ws.resources.BasicJacksonMarshallingResource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import static com.fasterxml.jackson.databind.SerializationFeature.WRAP_ROOT_VALUE;

/**
 * Created by adurlea on 23/11/18.
 */
@Component
public class BasicJacksonMarshallingResourceImpl implements BasicJacksonMarshallingResource {

    @Autowired
    private ObjectMapper objectMapper;

    // SERIALIZATION ANNOTATIONS

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

    @Override
    public Response getJsonRawValue() {
        JsonRawValueBean bean = new JsonRawValueBean("Using @JsonRawValue we can inject raw json in an variable " +
        "and having it serialized as an collection of plain elements for the variable element in the json response. " +
                "See difference between element [jsonRawValue] and [jsonNoRawValue]");
        bean.setJsonRawValue("{\"JsonRawValueAttr\":\"JsonRawValueVal\"}");
        bean.setJsonNoRawValue("{\"JsonNoRawValueAttr\":\"JsonNoRawValueVal\"}");
        return Response.ok().entity(bean).build();
    }

    @Override
    public Response getJsonValue() {
        return Response.ok().entity(JsonValueEnum.JSON_VALUE_1).build();
    }

    @Override
    public Response getJsonRootName() {
        JsonRootNameBean bean = new JsonRootNameBean();
        bean.setId(1);
        bean.setName("Using @JsonRootName, when wrapping is enabled, allow you to specify the root name to use in the " +
                "serialisation of the entity. " +
                "In this example I used @JsonRootName(\"explication\") on the bean JsonRootNameBean " +
                "This means that instead of serializing something like " +
                "{\"JsonRootNameBean\" : { \"id\": 1, \"name\": \"some explication\" } }  we will have " +
                "{ \"explication\" : { \"id\": 1, \"name\": \"some explication\" } }");

        String beanJson;
        try {
            beanJson = objectMapper.copy().enable(WRAP_ROOT_VALUE).writeValueAsString(bean);
        } catch (JsonProcessingException e) {
            return Response.serverError().entity("Error deserializing json").build();
        }

        return Response.ok().entity(beanJson).build();
    }

    @Override
    public Response getJsonSerialize() {
        JsonSerializeBean bean = new JsonSerializeBean();
        bean.setName("Using @JsonSerialize allow to use a custom serializer to serializer the entity. " +
                "See difference between [serializedDate] using annotation and " +
                "[noSerializedDate] not using the annotation");
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        try {
            bean.setSerializedDate(df.parse("29-11-2018 12:00:00"));
            bean.setNoSerializedDate(df.parse("29-11-2018 12:00:00"));
        } catch (ParseException e) {
            return Response.serverError().entity("Error parsing date 29-11-2018 12:00:00").build();
        }

        return Response.ok().entity(bean).build();
    }

    // DESERIALIZATION ANNOTATIONS


    @Override
    public Response postJsonCreator(JsonCreatorBean bean) {
        if(bean != null){
            return Response.ok().entity(bean).build();
        } else {
            return Response.serverError().entity("Error deserialize entity").build();
        }
    }

    @Override
    public Response postJacksonInject(String json, Integer id) {
        if (StringUtils.isBlank(json)) {
            return Response.serverError().entity("Parameter json invalide").build();
        }

        JacksonInjectBean bean;
        try {
            bean = objectMapper.reader(new InjectableValues.Std().addValue(Integer.class, id))
                    .forType(JacksonInjectBean.class).readValue(json);
        } catch (IOException e) {
            return Response.serverError().entity("Error deserializing json").build();
        }

        return Response.ok().entity(bean).build();
    }

    @Override
    public Response postJsonAnySetter(JsonAnySetterBean bean) {
        if(bean != null){
            return Response.ok().entity(bean).build();
        } else {
            return Response.serverError().entity("Error deserialize entity").build();
        }
    }

    @Override
    public Response postJsonSetter(JsonSetterBean bean) {
        if(bean != null){
            return Response.ok().entity(bean).build();
        } else {
            return Response.serverError().entity("Error deserialize entity").build();
        }
    }

    @Override
    public Response postJsonDeserialize(JsonDeserializeBean bean) {
        if(bean != null){
            return Response.ok().entity(bean).build();
        } else {
            return Response.serverError().entity("Error deserialize entity").build();
        }
    }
}
