package org.adurlea.spring.boot.ws.entities;


import com.fasterxml.jackson.annotation.JsonAnyGetter;

import java.util.Map;

/**
 * Created by adurlea on 23/11/18.
 */
public class BasicJacksonMarshallingBean {
    private String name;

    private Map<String, String> propertiesJsonAnyGetter;

    private Map<String, String> propertiesNoJsonAnyGetter;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonAnyGetter
    public Map<String, String> getProperties() {
        return propertiesJsonAnyGetter;
    }

    public void setProperties(Map<String, String> propertiesJsonAnyGetter) {
        this.propertiesJsonAnyGetter = propertiesJsonAnyGetter;
    }

    public Map<String, String> getPropertiesNoJsonAnyGetter() {
        return propertiesNoJsonAnyGetter;
    }

    public void setPropertiesNoJsonAnyGetter(Map<String, String> propertiesNoJsonAnyGetter) {
        this.propertiesNoJsonAnyGetter = propertiesNoJsonAnyGetter;
    }
}
