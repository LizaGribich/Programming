package org.lab7.server.commands;

import org.lab7.server.*;
import org.lab7.server.arguments.IntArgument;
import org.lab7.server.models.Vehicle;

import java.util.Arrays;

public class RemoveKey implements Comandable, IntArgument {

    static String name = "remove_key";
    private MapWrapper<Integer, Vehicle> hashMap;
    public RemoveKey (MapWrapper<Integer, Vehicle> hashMap) {
        this.hashMap = hashMap;
    }

    public static String getName() {
        return name;
    }

    @Override
    public CommandResult execute(Object... o) {
        String inputString = Arrays.toString(o);
        String[] inputValues = inputString.substring(1, inputString.length() - 1).split(" ");
        String idS = inputValues[0].substring(0, inputValues[0].length() - 1);
        int id = Integer.parseInt(idS);
        String userName = inputValues[1];
        CommandResult commandResult;
        if (hashMap.get(id) != null) {
            if (!hashMap.get(id).getUserName().equals(userName)) {
                commandResult = new CommandResult("Модель с id = " + id + " не ваша!", false);
            } else {
                hashMap.remove(id);
                Database.remove(id);
                commandResult = new CommandResult("Из коллекции удалён элемент с id = " + id + ".", true, true);
            }

        } else {
            commandResult = new CommandResult("Модель с id = " + id + " не существует!", false);
        }
        return commandResult;

    }



    @Override
    public String getDescr() {
        return "Удалить элемент из коллекции по его ключу.\n" +
                "Синтаксис: remove_key {id}";
    }


}
