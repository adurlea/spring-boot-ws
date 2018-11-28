package org.adurlea.spring.boot.ws.entities;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Created by adurlea on 28/11/18.
 */
public enum JsonValueEnum {
    JSON_VALUE_1(1, "JSON VALUE 1"),
    JSON_VALUE_2(2, "JSON VALUE 2");

    private int id;
    private String name;


    JsonValueEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    @JsonValue
    public String getName() {
        return name;
    }
}
