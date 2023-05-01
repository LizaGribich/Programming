package org.lab6.server.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.lab6.server.IDable;

import java.util.Date;

public class Vehicle implements Comparable<Vehicle>, IDable {
    private int id;
    private String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a z")
    private Date creationDate;
    private Double enginePower;
    private float capacity;
    private VehicleType type;
    private FuelType fuelType;
    public Vehicle() {

    }

    public Vehicle(int id, String name, Coordinates coordinates, Date creationDate,
                   Double enginePower, float capacity, VehicleType type, FuelType fuelType) {
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
        this.enginePower = enginePower;
        this.capacity = capacity;
        this.type = type;
        this.fuelType = fuelType;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public Double getEnginePower() {
        return enginePower;
    }
    public void setEnginePower(Double enginePower) {
        this.enginePower = enginePower;
    }
    public float getCapacity() {
        return capacity;
    }
    public void setCapacity(float capacity) {
        this.capacity = capacity;
    }

    public VehicleType getType() {
        return type;
    }

    @Override
    public String toString() {
        return " Name: " + this.getName() + "; Vehicle type: " + this.getType() + "; Engine power: " + this.getEnginePower() + "; Capacity: " + this.getCapacity();
    }

    @Override
    public int compareTo(Vehicle o) {
        if (this.getEnginePower() > o.getEnginePower()) {
            return 1;
        } else if (this.getEnginePower() == o.getEnginePower()) {
            return 0;
        } else {
            return -1;
        }
    }

    @Override
    public int detId() {
        return id;
    }
}
