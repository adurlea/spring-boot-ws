package org.adurlea.spring.boot.ws.entities;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by adurlea on 23/11/18.
 */
public class HelloWorldBean {
    private String salut = "Hello";

    private String who = "World";

    public HelloWorldBean() {
    }

    public String getSalut() {
        return salut;
    }

    public void setSalut(String hello) {
        this.salut = hello;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
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
