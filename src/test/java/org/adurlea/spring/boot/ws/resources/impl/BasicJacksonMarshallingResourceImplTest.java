package org.adurlea.spring.boot.ws.resources.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.adurlea.spring.boot.ws.conf.SpringBootWsApplication;
import org.adurlea.spring.boot.ws.entities.JsonAnyGetterBean;
import org.adurlea.spring.boot.ws.entities.JsonGetterBean;
import org.adurlea.spring.boot.ws.entities.JsonPropertyOrderBean;
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
        JsonAnyGetterBean expectedBean = new JsonAnyGetterBean();
        expectedBean.setName("@JsonAnyGetter: When used the elements of a Map will be shown as single json plain properties " +
                "and no as a collection of json plain properties! " +
                "See difference between propertiesJsonAnyGetter = [(JsonAnyGetter1, JsonAnyGetterVal1)," +
                "(JsonAnyGetter2, JsonAnyGetterVal2)] " +
                "and propertiesNoJsonAnyGetter = [(JsonAnyGetter1No, JsonAnyGetterVal1No)," +
                "(JsonAnyGetter2No, JsonAnyGetterVal2No)] ");
        Map<String, String> properties = new HashMap<>();
        properties.put("JsonAnyGetter1", "JsonAnyGetterVal1");
        properties.put("JsonAnyGetter2", "JsonAnyGetterVal2");
        expectedBean.setProperties(properties);

        Map<String, String> propertiesNo = new HashMap<>();
        propertiesNo.put("JsonAnyGetter1No", "JsonAnyGetterVal1No");
        propertiesNo.put("JsonAnyGetter2No", "JsonAnyGetterVal2No");
        expectedBean.setPropertiesNoJsonAnyGetter(propertiesNo);

        String expectedJson = new ObjectMapper().writeValueAsString(expectedBean);


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
        JsonPropertyOrderBean expectedBean = new JsonPropertyOrderBean(1,
                "Using @JsonPropertyOrder will show this as first element on the result " +
                        "even if it is declared as 2nd element");

        String expectedJson = new ObjectMapper().writeValueAsString(expectedBean);

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
}