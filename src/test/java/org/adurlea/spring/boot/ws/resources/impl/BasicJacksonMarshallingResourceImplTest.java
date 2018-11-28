package org.adurlea.spring.boot.ws.resources.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.adurlea.spring.boot.ws.conf.SpringBootWsApplication;
import org.adurlea.spring.boot.ws.entities.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.ws.rs.core.Response;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by adurlea on 28/11/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SpringBootWsApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class BasicJacksonMarshallingResourceImplTest {
    private static final String URL = "http://localhost:{0}/basicJacksonMarshalling/{1}";

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void when_getJsonAnyGetter() throws Exception {
        // GIVEN
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

        String expectedJson = getExpectedJson(bean, false);


        // WHEN
        ResponseEntity<String> entity = restTemplate.getForEntity(MessageFormat.format(URL, String.valueOf(this.port),
                "jsonAnyGetter"), String.class);

        // THEN
        Assert.assertNotNull(entity);
        Assert.assertEquals(Response.ok().build().getStatus(), entity.getStatusCodeValue());
        String resultedJson = entity.getBody();
        Assert.assertNotNull(resultedJson);
        Assert.assertEquals(expectedJson, resultedJson);
    }

    @Test
    public void when_getJsonGetter() throws Exception {
        // GIVEN
        JsonGetterBean expectedBean = new JsonGetterBean();
        expectedBean.setId(1);
        expectedBean.setNameJsonGetter("Contains @JsonGetter on the theNameJsonGetter method and will be shown as response");

        // WHEN
        ResponseEntity<JsonGetterBean> entity = restTemplate.getForEntity(MessageFormat.format(URL,
                String.valueOf(this.port), "jsonGetter"), JsonGetterBean.class);

        // THEN
        Assert.assertNotNull(entity);
        Assert.assertEquals(Response.ok().build().getStatus(), entity.getStatusCodeValue());
        JsonGetterBean resultedBean = entity.getBody();
        Assert.assertNotNull(resultedBean);
        Assert.assertEquals(expectedBean, resultedBean);
    }

    @Test
    public void when_getJsonPropertyOrder() throws Exception {
        // GIVEN
        JsonPropertyOrderBean bean = new JsonPropertyOrderBean(1,
                "Using @JsonPropertyOrder will show this as first element on the result " +
                        "even if it is declared as 2nd element");

        String expectedJson = getExpectedJson(bean, false);

        // WHEN
        ResponseEntity<String> entity = restTemplate.getForEntity(MessageFormat.format(URL,
                String.valueOf(this.port), "jsonPropertyOrder"), String.class);

        // THEN
        Assert.assertNotNull(entity);
        Assert.assertEquals(Response.ok().build().getStatus(), entity.getStatusCodeValue());
        String resultedJson = entity.getBody();
        Assert.assertNotNull(resultedJson);
        Assert.assertEquals(expectedJson, resultedJson);
    }

    @Test
    public void when_getJsonRawValue() throws Exception {
        // GIVEN
        JsonRawValueBean bean = new JsonRawValueBean("Using @JsonRawValue we can inject raw json in an variable " +
                "and having it serialised as collection of plain elements for the variable element in the json response. " +
                "See difference between element [jsonRawValue] and [jsonNoRawValue]");
        bean.setJsonRawValue("{\"JsonRawValueAttr\":\"JsonRawValueVal\"}");
        bean.setJsonNoRawValue("{\"JsonNoRawValueAttr\":\"JsonNoRawValueVal\"}");

        String expectedJson = getExpectedJson(bean, false);

        // WHEN
        ResponseEntity<String> entity = restTemplate.getForEntity(MessageFormat.format(URL,
                String.valueOf(this.port), "jsonRawValue"), String.class);

        // THEN
        Assert.assertNotNull(entity);
        Assert.assertEquals(Response.ok().build().getStatus(), entity.getStatusCodeValue());
        String resultedJson = entity.getBody();
        Assert.assertNotNull(resultedJson);
        Assert.assertEquals(expectedJson, resultedJson);
    }

    @Test
    public void when_getJsonValue() throws Exception {
        // GIVEN
        String expectedJson = getExpectedJson(JsonValueEnum.JSON_VALUE_1, false);

        // WHEN
        ResponseEntity<String> entity = restTemplate.getForEntity(MessageFormat.format(URL,
                String.valueOf(this.port), "jsonValue"), String.class);

        // THEN
        Assert.assertNotNull(entity);
        Assert.assertEquals(Response.ok().build().getStatus(), entity.getStatusCodeValue());
        String resultedJson = entity.getBody();
        Assert.assertNotNull(resultedJson);
        Assert.assertEquals(expectedJson, resultedJson);
    }

    @Test
    public void when_getJsonRootName() throws Exception {
        // GIVEN
        JsonRootNameBean bean = new JsonRootNameBean();
        bean.setId(1);
        bean.setName("Using @JsonRootName, when wrapping is enabled, allow you to specify the root name to use in the " +
                "serialisation of the entity. " +
                "In this example I used @JsonRootName(\"explication\") on the bean JsonRootNameBean " +
                "This means that instead of serializing something like " +
                "{\"JsonRootNameBean\" : { \"id\": 1, \"name\": \"some explication\" } }  we will have " +
                "{ \"explication\" : { \"id\": 1, \"name\": \"some explication\" } }");

        String expectedJson = getExpectedJson(bean, true);

        // WHEN
        ResponseEntity<String> entity = restTemplate.getForEntity(MessageFormat.format(URL,
                String.valueOf(this.port), "jsonRootName"), String.class);

        // THEN
        Assert.assertNotNull(entity);
        Assert.assertEquals(Response.ok().build().getStatus(), entity.getStatusCodeValue());
        String resultedJson = entity.getBody();
        Assert.assertNotNull(resultedJson);
        Assert.assertEquals(expectedJson, resultedJson);
    }

    private String getExpectedJson(Object bean, boolean isWrapEnabled) throws JsonProcessingException {
        return new
                ObjectMapper().configure(SerializationFeature.WRAP_ROOT_VALUE, isWrapEnabled).writeValueAsString(bean);
    }

}