package org.adurlea.spring.boot.ws.resources.impl;

import org.adurlea.spring.boot.ws.conf.SpringBootWsApplication;
import org.adurlea.spring.boot.ws.entities.HelloWorldBean;
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


/**
 * Created by adurlea on 28/11/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SpringBootWsApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class HelloWorldResourceImplTest {
    private static final String HTTP_LOCALHOST = "http://localhost:";

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void when_getHelloWorld() {
        // GIVEN
        HelloWorldBean expectedBean = new HelloWorldBean();

        // WHEN
        ResponseEntity<HelloWorldBean> entity =
                restTemplate.getForEntity(HTTP_LOCALHOST + this.port + "/helloWorld", HelloWorldBean.class);

        // THEN
        Assert.assertNotNull(entity);
        Assert.assertEquals(Response.ok().build().getStatus(), entity.getStatusCodeValue());
        HelloWorldBean resultedBean = entity.getBody();
        Assert.assertNotNull(resultedBean);
        Assert.assertEquals(expectedBean, resultedBean);
    }
}