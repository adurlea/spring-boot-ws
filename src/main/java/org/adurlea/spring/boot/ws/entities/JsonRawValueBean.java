package org.adurlea.spring.boot.ws.entities;


import com.fasterxml.jackson.annotation.JsonRawValue;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


/**
 * Created by adurlea on 28/11/18.
 */
public class JsonRawValueBean {
    private String name;

    @JsonRawValue
    private String jsonRawValue;

    private String jsonNoRawValue;

    public JsonRawValueBean(String name) {
        this.name = name;
    }

    public void setJsonRawValue(String jsonRawValue) {
        this.jsonRawValue = jsonRawValue;
    }

    public String getName() {
        return name;
    }

    public String getJsonRawValue() {
        return jsonRawValue;
    }

    public String getJsonNoRawValue() {
        return jsonNoRawValue;
    }

    public void setJsonNoRawValue(String jsonNoRawValue) {
        this.jsonNoRawValue = jsonNoRawValue;
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
