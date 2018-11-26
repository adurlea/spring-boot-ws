package org.adurlea.spring.boot.ws.entities;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


/**
 * Created by adurlea on 26/11/18.
 */
@JsonPropertyOrder({"name", "id"})
public class JsonPropertyOrderBean {
    public int id;
    public String name;

    public JsonPropertyOrderBean(int id, String name) {
        this.id = id;
        this.name = name;
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
