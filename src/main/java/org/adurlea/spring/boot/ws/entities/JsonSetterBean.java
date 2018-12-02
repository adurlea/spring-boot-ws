package org.adurlea.spring.boot.ws.entities;


import com.fasterxml.jackson.annotation.JsonSetter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


/**
 * Created by adurlea on 02/12/18.
 */
public class JsonSetterBean {
    public int id;
    private String bookNameJsonSetter;
    private String bookNameNoJsonSetter;

    public void setId(int id) {
        this.id = id;
    }

    public String getBookNameJsonSetter() {
        return bookNameJsonSetter;
    }

    @JsonSetter("nameJsonSetter")
    public void setBookNameJsonSetter(String bookNameJsonSetter) {
        this.bookNameJsonSetter = bookNameJsonSetter;
    }

    public String getBookNameNoJsonSetter() {
        return bookNameNoJsonSetter;
    }

    public void setBookNameNoJsonSetter(String bookNameNoJsonSetter) {
        this.bookNameNoJsonSetter = bookNameNoJsonSetter;
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
