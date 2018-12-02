package org.adurlea.spring.boot.ws.entities;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.adurlea.spring.boot.ws.tools.CustomDateDeserializer;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

/**
 * Created by adurlea on 02/12/18.
 */
public class JsonDeserializeBean {
    private String name;

    @JsonDeserialize(using = CustomDateDeserializer.class)
    private Date deserializedDate;

    private Date noDeserializedDate;

    public JsonDeserializeBean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDeserializedDate() {
        return deserializedDate;
    }

    public void setDeserializedDate(Date deserializedDate) {
        this.deserializedDate = deserializedDate;
    }

    public Date getNoDeserializedDate() {
        return noDeserializedDate;
    }

    public void setNoDeserializedDate(Date noDeserializedDate) {
        this.noDeserializedDate = noDeserializedDate;
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
