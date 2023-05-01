package org.lab6.server.commands;

import org.lab6.server.*;
import org.lab6.server.models.Vehicle;

import java.util.Arrays;

public class ReplaceIfGreater implements Comandable {
    static String name = "replace_if_greater";
    private MapWrapper<Integer, Vehicle> hashMap;
    public ReplaceIfGreater (MapWrapper<Integer, Vehicle> hashMap) throws Exception {
        this.hashMap = hashMap;
    }


    public CommandResult execute(Object... o) {
        String inputString = Arrays.toString(o);
        System.out.println(inputString);
        String[] inputValues = inputString.substring(1, inputString.length() - 1).split(" ");

        int id = Integer.parseInt(inputValues[0]);
        String field = inputValues[1];
        Number value = Double.parseDouble(inputValues[2]);


        CommandResult commandResult;
        if (hashMap.get(id) != null) {
            if (field.equals("enginePower")) {
                Number enginePower = value;
                if (hashMap.get(id).getEnginePower() - enginePower.doubleValue() < 0) {
                    hashMap.get(id).setEnginePower(enginePower.doubleValue());
                    commandResult = new CommandResult("Значение enginePower успешно изменено!", true);
                } else {
                    commandResult = new CommandResult("Введённое значение" +
                            " не больше уже установленного.\nЗначение enginePower не изменено.", false);
                }
            } else if (field.equals("capacity")) {
                Number capacity =  value;
                if (hashMap.get(id).getCapacity() - capacity.floatValue() < 0) {
                    hashMap.get(id).setCapacity(capacity.floatValue());
                    commandResult = new CommandResult("Значение capacity успешно изменено!", true);
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
