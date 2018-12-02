package org.adurlea.spring.boot.ws.entities;


import com.fasterxml.jackson.annotation.JsonAnySetter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by adurlea on 01/12/18.
 */
public class JsonAnySetterBean {
    private String name;

    private Map<String, String> propertiesJsonAnySetter = new HashMap<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getPropertiesJsonAnySetter() {
        return propertiesJsonAnySetter;
    }

    @JsonAnySetter
    public void addProperties(String key, String value) {
        this.propertiesJsonAnySetter.put(key, value);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }
}
