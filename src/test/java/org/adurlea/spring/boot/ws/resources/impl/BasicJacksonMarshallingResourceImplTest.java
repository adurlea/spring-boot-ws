package org.adurlea.spring.boot.ws.resources.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.adurlea.spring.boot.ws.conf.SpringBootWsApplication;
import org.adurlea.spring.boot.ws.entities.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;

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

    @Before
    public void setUp(){
        restTemplate.getRestTemplate().setInterceptors(
                Collections.singletonList(
                        (request, body, execution) -> {
                            request.getHeaders().clear();
                            request.getHeaders().add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON + ";charset=utf-8");
                            request.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON + ";charset=utf-8");
                            return execution.execute(request, body);
                        }));
    }

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

        ResponseEntity<String> entity = restTemplate.
                getForEntity(MessageFormat.format(URL, String.valueOf(this.port),
                "jsonAnyGetter"), String.class);

        // THEN
        Assert.assertNotNull(entity);
        Assert.assertEquals(Response.Status.OK.getStatusCode(), entity.getStatusCodeValue());
        String resultedJson = entity.getBody();
        Assert.assertNotNull(resultedJson);
        Assert.assertEquals(expectedJson, resultedJson);
    }

    @Test
    public void when_getJsonGetter() {
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
                "and having it serialized as an collection of plain elements for the variable element in the json response. " +
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

    @Test
    public void when_getJsonSerialize() throws Exception {
        // GIVEN
        JsonSerializeBean bean = new JsonSerializeBean();
        bean.setName("Using @JsonSerialize allow to use a custom serializer to serializer the entity. " +
                "See difference between [serializedDate] using annotation and " +
                "[noSerializedDate] not using the annotation");
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        bean.setSerializedDate(df.parse("29-11-2018 12:00:00"));
        bean.setNoSerializedDate(df.parse("29-11-2018 12:00:00"));

        String expectedJson = getExpectedJson(bean, false);

        // WHEN
        ResponseEntity<String> entity = restTemplate.getForEntity(MessageFormat.format(URL,
                String.valueOf(this.port), "jsonSerialize"), String.class);

        // THEN
        Assert.assertNotNull(entity);
        Assert.assertEquals(Response.ok().build().getStatus(), entity.getStatusCodeValue());
        String resultedJson = entity.getBody();
        Assert.assertNotNull(resultedJson);
        Assert.assertEquals(expectedJson, resultedJson);
    }

    @Test
    public void when_postJsonCreator() throws Exception {
        // GIVEN
        String expectedName = "Using @JsonCreator with @JsonProperty can be usefull when the json " +
        "sent contans an element or elements  with other name than our entity. " +
                "This way the deserializer can match the received names with the entity names " +
                "without needing to change the entity. " +
                "See difference between input and output autour de element someName";
        int expectedId = 1;
        String json = "{\"id\":" + expectedId + ",\"someName\":\"" + expectedName + "\"}";

        // WHEN
        ResponseEntity<JsonCreatorBean> entity = restTemplate.postForEntity(MessageFormat.format(URL,
                String.valueOf(this.port), "jsonCreator"), json, JsonCreatorBean.class);


        // THEN
        Assert.assertNotNull(entity);
        Assert.assertEquals(Response.ok().build().getStatus(), entity.getStatusCodeValue());
        JsonCreatorBean resultedbean = entity.getBody();
        Assert.assertNotNull(resultedbean);
        Assert.assertEquals(expectedId, resultedbean.getId());
        Assert.assertEquals(expectedName, resultedbean.getName());
    }

    @Test
    public void when_postJacksonInject() throws Exception {
        // GIVEN
        String expectedName = "Using @JacksonInject can be useful when we want to inject a value " +
                "that is not from the json data. In this example I'm using the value provided by path param " +
                "to inject it into {id} variable of JacksonInjectBean along with the json data in the body.";
        int expectedId = 10;
        String json = "{\"name\":\"" + expectedName + "\"}";

        // WHEN
        ResponseEntity<JsonCreatorBean> entity = restTemplate.postForEntity(MessageFormat.format(URL,
                String.valueOf(this.port), "jacksonInject/" + expectedId), json, JsonCreatorBean.class);


        // THEN
        Assert.assertNotNull(entity);
        Assert.assertEquals(Response.ok().build().getStatus(), entity.getStatusCodeValue());
        JsonCreatorBean resultedbean = entity.getBody();
        Assert.assertNotNull(resultedbean);
        Assert.assertEquals(expectedId, resultedbean.getId());
        Assert.assertEquals(expectedName, resultedbean.getName());
    }

    @Test
    public void when_postJsonAnySetter() {
        // GIVEN
        JsonAnySetterBean expectedBean = new JsonAnySetterBean();
        expectedBean.setName("@JsonAnySetter is the corespondent of @JsonAnyGetter but is used for deserialization. " +
                "It allow us the flexibility to add the plain elements of the json to a map. " +
                "See difference between input and output.");
        expectedBean.addProperties("jsonAnySetterAttr1", "jsonAnySetterVal1");
        expectedBean.addProperties("jsonAnySetterAttr2", "jsonAnySetterVal2");
        String json = "{\"name\":\"" + expectedBean.getName() + "\"," +
                "\"jsonAnySetterAttr1\":\"jsonAnySetterVal1\"," +
                "\"jsonAnySetterAttr2\":\"jsonAnySetterVal2\"" +
                "}";


        // WHEN
        ResponseEntity<JsonAnySetterBean> entity = restTemplate.postForEntity(MessageFormat.format(URL,
                String.valueOf(this.port), "jsonAnySetter"), json, JsonAnySetterBean.class);


        // THEN
        Assert.assertNotNull(entity);
        Assert.assertEquals(Response.ok().build().getStatus(), entity.getStatusCodeValue());
        JsonAnySetterBean resultedbean = entity.getBody();
        Assert.assertNotNull(resultedbean);
        Assert.assertEquals(expectedBean, resultedbean);
    }

    @Test
    public void when_postJsonSetter() {
        // GIVEN
        JsonSetterBean expectedBean = new JsonSetterBean();
        expectedBean.setId(1);
        expectedBean.setBookNameJsonSetter("Non conform setter using @JsonSetter and it will be shown in the output.");
        String json = "{\"id\":1," +
                "\"nameJsonSetter\":\"Non conform setter using @JsonSetter and it will be shown in the output.\"," +
                "\"nameNoJsonSetter\":\"Non conform setter not using @JsonSetter and it will not be shown in the output.\"" +
                "}";


        // WHEN
        ResponseEntity<JsonSetterBean> entity = restTemplate.postForEntity(MessageFormat.format(URL,
                String.valueOf(this.port), "jsonSetter"), json, JsonSetterBean.class);


        // THEN
        Assert.assertNotNull(entity);
        Assert.assertEquals(Response.ok().build().getStatus(), entity.getStatusCodeValue());
        JsonSetterBean resultedbean = entity.getBody();
        Assert.assertNotNull(resultedbean);
        Assert.assertEquals(expectedBean, resultedbean);
    }

    /*@Test
    public void when_postJsonDeserialize() throws ParseException {
        // GIVEN
        JsonDeserializeBean expectedBean = new JsonDeserializeBean();
        expectedBean.setName("When used @JsonDeserialize we can use a custom deserializer to deserializer the entity. " +
                "See the date format difference between [serializedDate] using annotation and [noSerializedDate] " +
                "not using the annotation");
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        expectedBean.setDeserializedDate(df.parse("02-12-2018 12:00:00"));
        expectedBean.setNoDeserializedDate(df.parse("02-12-2018 12:00:00"));

        String json = "{\"name\":\"" + expectedBean.getName() + "\"," +
                "\"deserializedDate\":\"02-12-2018 23:00:00\"," +
                "\"noDeserializedDate\":\"2018-12-02T23:00:00.000\"" +
                "}";


        // WHEN
        ResponseEntity<JsonDeserializeBean> entity = restTemplate.postForEntity(MessageFormat.format(URL,
                String.valueOf(this.port), "jsonDeserialize"), json, JsonDeserializeBean.class);


        // THEN
        Assert.assertNotNull(entity);
        Assert.assertEquals(Response.ok().build().getStatus(), entity.getStatusCodeValue());
        JsonDeserializeBean resultedbean = entity.getBody();
        Assert.assertNotNull(resultedbean);
        Assert.assertEquals(expectedBean, resultedbean);
    }*/

    private String getExpectedJson(Object bean, boolean isWrapEnabled) throws JsonProcessingException {
        return new ObjectMapper()
                .disable(WRITE_DATES_AS_TIMESTAMPS)
                .configure(SerializationFeature.WRAP_ROOT_VALUE, isWrapEnabled)
                .writeValueAsString(bean);
    }
}