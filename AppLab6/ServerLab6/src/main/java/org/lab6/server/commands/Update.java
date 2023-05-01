package org.lab6.server.commands;


import org.lab6.server.*;
import org.lab6.server.models.Coordinates;
import org.lab6.server.models.FuelType;
import org.lab6.server.models.Vehicle;
import org.lab6.server.models.VehicleType;

import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;


public class Update implements Comandable {
    static String name = "update";
    private MapWrapper<Integer, Vehicle> hashMap;

    public Update(MapWrapper<Integer, Vehicle> hashMap) {
        this.hashMap = hashMap;
    }


    public static String getName() {
        return name;
    }

    @Override
    public String getDescr() {
        return "Обновить значение элемента коллекции, id которого равен заданному.\n" +
                "Синтаксис: update {id}";
    }

    @Override
    public CommandResult execute(Object... o) {
        ConsolePrinter consolePrinter = new ConsolePrinter();

        String inputString = Arrays.toString(o);
        String[] inputValues = inputString.substring(1, inputString.length() - 1).split(" ");

        int id = Integer.parseInt(inputValues[0]);
        String name = inputValues[1];
        Double x = Double.parseDouble(inputValues[2]);
        float y = Float.parseFloat(inputValues[3]);
        Double enginePower = Double.parseDouble(inputValues[4]);
        float capacity = Float.parseFloat(inputValues[5]);
        String type = inputValues[6];
        String fuelType = inputValues[7];

        Date creationDate = new Date(ThreadLocalRandom.current().nextInt() * 1000L);


        CommandResult commandResult;
        if (hashMap.get(id) != null) {
            hashMap.put(id, new Vehicle(id, name, new Coordinates(x, y), creationDate, enginePower, capacity,
                    VehicleType.valueOf(type), FuelType.valueOf(fuelType)));
            commandResult = new CommandResult("Модель с id = " + id +
                    " успешно заменена!", true);
        } else {
            commandResult = new CommandResult("Модель с id = " + id +
                    " не существует!", false);
        }
        return commandResult;
    }

}
