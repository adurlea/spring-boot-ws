package org.adurlea.spring.boot.ws.entities;


import com.fasterxml.jackson.annotation.JsonGetter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


/**
 * Created by adurlea on 26/11/18.
 */
public class JsonGetterBean {
    public int id;
    private String nameJsonGetter;
    private String nameNoJsonGetter;

    public void setId(int id) {
        this.id = id;
    }

    @JsonGetter("nameJsonGetter")
    public String theNameJsonGetter() {
        return nameJsonGetter;
    }

    public void setNameJsonGetter(String nameJsonGetter) {
        this.nameJsonGetter = nameJsonGetter;
    }

    public String theNameNoJsonGetter() {
        return nameNoJsonGetter;
    }

    public void setNameNoJsonGetter(String nameNoJsonGetter) {
        this.nameNoJsonGetter = nameNoJsonGetter;
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
