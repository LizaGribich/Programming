package org.lab6.client;

import org.lab6.client.commands.EnterField;
import org.lab6.client.models.*;


import java.util.Date;

import java.util.concurrent.ThreadLocalRandom;

public class CreationModel {
    ConsolePrinter consolePrinter = new ConsolePrinter();
    public Vehicle createModel(Integer id) {
        EnterField enterField = new EnterField();
        String name = enterField.enterString("name");
        Double x = enterField.enterDouble("coordinates x");
        float y = enterField.enterFloat("coordinates y");

        Date creationDate = new Date(ThreadLocalRandom.current().nextInt() * 1000L);
        consolePrinter.printToConsole("Поле creationDate генерируется автоматически:" + creationDate);

        Double enginePower = enterField.enterDouble("enginePower");
        float capacity = enterField.enterFloat("capacity");

        String typeString = enterField.enterString("type (CAR, DRONE, CHOPPER, HOVERBOARD, SPACESHIP)");

        while (true) {
            try {
                VehicleType.valueOf(typeString);
                break;
            } catch (IllegalArgumentException e) {
                consolePrinter.printToConsole("Такого значения не существует.");
                typeString = enterField.enterString("type (CAR, DRONE, CHOPPER, HOVERBOARD, SPACESHIP)");
            }
        }

        String fuelTypeString = enterField.enterString("fuelType (GASOLINE, MANPOWER, PLASMA, ANTIMATTER)");

        while (true) {
            try {
                FuelType.valueOf(fuelTypeString);
                break;
            } catch (IllegalArgumentException e) {
                consolePrinter.printToConsole("Такого значения не существует.");
                fuelTypeString = enterField.enterString("fuelType (GASOLINE, MANPOWER, PLASMA, ANTIMATTER)");
            }
        }

        return new Vehicle(id, name, new Coordinates(x, y), creationDate, enginePower, capacity,
                VehicleType.valueOf(typeString), FuelType.valueOf(fuelTypeString));

    }
}
