package org.lab7.server.commands;

import org.lab7.server.*;
import org.lab7.server.models.Vehicle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Clear implements Comandable {
    static String name = "clear";
    private MapWrapper<Integer, Vehicle> hashMap;
    public Clear (MapWrapper<Integer, Vehicle> hashMap) {
        this.hashMap = hashMap;
    }

    public static String getName() {
        return name;
    }

    @Override
    public CommandResult execute(Object... o) {
        String inputString = Arrays.toString(o);
        String[] inputValues = inputString.substring(1, inputString.length() - 1).split(" ");
        String userName = inputValues[1];
        String result = "";

        List<Integer> keysForRemoving = new ArrayList<>();

        for (int key : hashMap.keySet()) {
            System.out.println(hashMap.get(key).getUserName());
            if (hashMap.get(key).getUserName().equals(userName)) {
                keysForRemoving.add(key);
            }
        }
        for (int key : keysForRemoving) {
            hashMap.remove(key);
            Database.remove(key);
            result += "Из коллекции был удалён эменет с id = " + key + "\n";

        }

        return new CommandResult(result, true, true);
    }

    @Override
    public String getDescr() {
        return "Очистить коллекцию.\n" +
                "Синтаксис: clear";
    }


}
