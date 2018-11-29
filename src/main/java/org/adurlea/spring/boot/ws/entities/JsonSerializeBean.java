package org.adurlea.spring.boot.ws.entities;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.adurlea.spring.boot.ws.tools.CustomDateSerializer;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

/**
 * Created by adurlea on 29/11/18.
 */
public class JsonSerializeBean {
    private String name;

    @JsonSerialize(using = CustomDateSerializer.class)
    private Date serializedDate;

    private Date noSerializedDate;

    public JsonSerializeBean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getSerializedDate() {
        return serializedDate;
    }

    public void setSerializedDate(Date serializedDate) {
        this.serializedDate = serializedDate;
    }

    public Date getNoSerializedDate() {
        return noSerializedDate;
    }

    public void setNoSerializedDate(Date noSerializedDate) {
        this.noSerializedDate = noSerializedDate;
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
