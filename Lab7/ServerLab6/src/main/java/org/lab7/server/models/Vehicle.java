package org.lab7.server.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.lab7.server.IDable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    private String userName;
    public Vehicle() {

    }

    public Vehicle(int id, String name, Coordinates coordinates, Date creationDate,
                   Double enginePower, float capacity, VehicleType type, FuelType fuelType, String userName) {
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
        this.enginePower = enginePower;
        this.capacity = capacity;
        this.type = type;
        this.fuelType = fuelType;
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
    public String getUserName() {
        return userName;
    }
    public void setUser(String userName) {
        this.userName = userName;
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
    public void setType(String type) {
        this.type = VehicleType.valueOf(type);
    }
    public void setFuelType(String type) {
        this.fuelType = FuelType.valueOf(type);
    }

    public VehicleType getType() {
        return type;
    }
    public void setID(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setCreationDate(String creationDate) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd h:mm a z");
        Date date = dateFormat.parse(creationDate);
        this.creationDate = date;
    }
    @Override
    public String toString() {
        return " Name: " + this.getName() + "; Vehicle type: " + this.getType() + "; Engine power: " + this.getEnginePower() + "; Capacity: " + this.getCapacity() + "; User: " + this.getUserName();
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
