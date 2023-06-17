package org.lab7.server.commands;


import com.fasterxml.jackson.core.JacksonException;
import org.lab7.server.*;
import org.lab7.server.arguments.ExtraModel;
import org.lab7.server.arguments.IntArgument;
import org.lab7.server.models.Coordinates;
import org.lab7.server.models.FuelType;
import org.lab7.server.models.Vehicle;
import org.lab7.server.models.VehicleType;

import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;
import java.util.jar.JarException;


public class Update implements Comandable, IntArgument, ExtraModel {
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
        System.out.println(Arrays.toString(o));
        String inputString = Arrays.toString(o);
        System.out.println(inputString);
        String[] inputValues = inputString.substring(1, inputString.length() - 1).split(" ");
        System.out.println(inputString);
        try {
            int id = Integer.parseInt(inputValues[0]);
            String name = inputValues[1];
            Double x = Double.parseDouble(inputValues[2]);
            float y = Float.parseFloat(inputValues[3]);
            Double enginePower = Double.parseDouble(inputValues[4]);
            float capacity = Float.parseFloat(inputValues[5]);
            String type = inputValues[6];
            String fuelType = inputValues[7].substring(0, inputValues[7].length() - 1);
            String userName = inputValues[8];
            Date creationDate = new Date(ThreadLocalRandom.current().nextInt() * 1000L);

            System.out.println(type + id+ y);
            CommandResult commandResult;
            if (hashMap.get(id) != null) {
                if (!hashMap.get(id).getUserName().equals(userName)) {
                    commandResult = new CommandResult("Модель с id = " + id +
                            " не ваша!", false);
                } else {
                    hashMap.put(id, new Vehicle(id, name, new Coordinates(x, y), creationDate, enginePower, capacity,
                            VehicleType.valueOf(type), FuelType.valueOf(fuelType), userName));
                    Database.updateBase(id, name, creationDate, enginePower, capacity,
                            VehicleType.valueOf(type), FuelType.valueOf(fuelType), new Coordinates(x, y));
                    commandResult = new CommandResult("Модель с id = " + id +
                            " успешно заменена!", true, true);
                }

            } else {
                commandResult = new CommandResult("Модель с id = " + id +
                        " не существует!", false);
            }
            return commandResult;
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return new CommandResult("Неправильно введена команда.", false);
        }


    }

}
