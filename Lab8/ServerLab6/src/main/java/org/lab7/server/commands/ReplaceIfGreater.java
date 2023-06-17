package org.lab7.server.commands;

import org.lab7.server.*;
import org.lab7.server.arguments.ExtraData;
import org.lab7.server.arguments.IntArgument;
import org.lab7.server.models.Coordinates;
import org.lab7.server.models.FuelType;
import org.lab7.server.models.Vehicle;
import org.lab7.server.models.VehicleType;

import java.util.Arrays;
import java.util.Date;

public class ReplaceIfGreater implements Comandable, IntArgument, ExtraData {
    static String name = "replace_if_greater";
    private MapWrapper<Integer, Vehicle> hashMap;
    public ReplaceIfGreater (MapWrapper<Integer, Vehicle> hashMap) {
        this.hashMap = hashMap;
    }


    public CommandResult execute(Object... o) {
        String inputString = Arrays.toString(o);
        String[] inputValues = inputString.substring(1, inputString.length() - 1).split(" ");
        int id;
        String userName = inputValues[3];
        try {
            id = Integer.parseInt(inputValues[0]);
        } catch (NumberFormatException e) {
            return new CommandResult("Введен неверный тип данных в качетве аргумента.", false);
        }
        System.out.println(inputValues[1]);
        String field = inputValues[1];
        Number value;
        try {
            if (!hashMap.get(id).getUserName().equals(userName)) {
                return new CommandResult("Этот элемент не ваш.", false);
            }
        } catch (NullPointerException e) {
            return new CommandResult("Такого элемента не существует.", false);
        }

        try {
            System.out.println(inputValues[2]);
            value = Double.parseDouble((inputValues[2].substring(0, inputValues[2].length() - 1)));
        } catch (NumberFormatException e) {
            return new CommandResult("Введен неверный тип данных.", false);
        }



        CommandResult commandResult;
        if (hashMap.get(id) != null) {
            if (field.equals("enginePower")) {
                Number enginePower = value;
                if (hashMap.get(id).getEnginePower() - enginePower.doubleValue() < 0) {
                    hashMap.get(id).setEnginePower(enginePower.doubleValue());
                    float capacity = hashMap.get(id).getCapacity();
                    VehicleType type =  hashMap.get(id).getType();
                    FuelType fuelType = hashMap.get(id).getFuelType();
                    Coordinates coordinates = hashMap.get(id).getCoordinates();
                    Date creationDate = hashMap.get(id).getDate();
                    Database.updateBase(id, name, creationDate, enginePower.doubleValue(), capacity,
                            type, fuelType, coordinates);
                    commandResult = new CommandResult("Значение enginePower успешно изменено!", true, true);
                } else {
                    commandResult = new CommandResult("Введённое значение" +
                            " не больше уже установленного.\nЗначение enginePower не изменено.", false);
                }
            } else if (field.equals("capacity")) {
                Number capacity =  value;
                if (hashMap.get(id).getCapacity() - capacity.floatValue() < 0) {
                    hashMap.get(id).setCapacity(capacity.floatValue());
                    VehicleType type =  hashMap.get(id).getType();
                    FuelType fuelType = hashMap.get(id).getFuelType();
                    Coordinates coordinates = hashMap.get(id).getCoordinates();
                    Date creationDate = hashMap.get(id).getDate();
                    Double enginePower = hashMap.get(id).getEnginePower();
                    Database.updateBase(id, name, creationDate, enginePower, capacity.floatValue(),
                            type, fuelType, coordinates);
                    commandResult = new CommandResult("Значение capacity успешно изменено!", true, true);
                } else {
                    commandResult = new CommandResult("Введённое значение" +
                            " не больше уже установленного.\nЗначение capacity не изменено.", false);
                }
            } else {
                commandResult = new CommandResult("Введено неверное название поля.", false);
            }
        } else {
            commandResult = new CommandResult("Модель с id = " + id + " не существует!", false);
        }
        return commandResult;
    }



    @Override
    public String getDescr() {
        return "Заменить значение по ключу, если новое значение больше старого.\n" +
                "Синтаксис: replace_if_greater {id}";
    }
    public static String getName() {
        return name;
    }
}
