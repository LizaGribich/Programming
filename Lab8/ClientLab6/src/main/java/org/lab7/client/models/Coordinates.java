package org.lab7.client.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Coordinates {

    private Double x;
    private float y;
    public Coordinates(){};

    @JsonCreator
    public Coordinates(@JsonProperty("x") Double x,@JsonProperty("y") float y) {
        this.x = x;
        this.y = y;
    }

}
