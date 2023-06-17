package org.lab7.server.commands;

import org.lab7.server.*;
import org.lab7.server.arguments.IntArgument;
import org.lab7.server.models.Vehicle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RemoveLowerKey implements Comandable, IntArgument {
    static String name = "remove_lower_key";
    private MapWrapper<Integer, Vehicle> hashMap;
    public RemoveLowerKey (MapWrapper<Integer, Vehicle> hashMap) {
        this.hashMap = hashMap;
    }

    public static String getName() {
        return name;
    }

    @Override
    public CommandResult execute(Object... o){
        List<Integer> keysForRemoving = new ArrayList<>();
        String result = "";
        String inputString = Arrays.toString(o);
        String[] inputValues = inputString.substring(1, inputString.length() - 1).split(" ");
        String idS = inputValues[0].substring(0, inputValues[0].length() - 1);
        int id = Integer.parseInt(idS);
        String userName = inputValues[1];
        for (int key : hashMap.keySet()) {
            if (key < id) {
                keysForRemoving.add(key);
            }
        }
        for (int key : keysForRemoving) {
            if (!hashMap.get(key).getUserName().equals(userName)) {
                result += "Элемент с id = " + key + " не ваш!\n";
            } else {
                hashMap.remove(key);
                Database.remove(key);
                result += "Из коллекции был удалён эменет с id = " + key + "\n";
            }
        }
        return new CommandResult(result, true, true);
    }


    @Override
    public String getDescr() {
        return "Удалить из коллекции все элементы, ключ которых меньше, чем заданный.\n" +
                "Синтаксис: remove_lower_key {id}";
    }
}
