package org.lab7.server.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Coordinates {

    private Double x;
    private float y;

    @JsonCreator
    public Coordinates(@JsonProperty("x") Double x, @JsonProperty("y") float y) {
        this.x = x;
        this.y = y;
    }
    public Double getX() {
        return x;
    }
    public float getY() {
        return y;
    }

}
