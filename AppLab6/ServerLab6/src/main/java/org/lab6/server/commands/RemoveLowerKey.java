package org.lab6.server.commands;

import org.lab6.server.*;
import org.lab6.server.models.Vehicle;

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
        int id = Integer.valueOf(Arrays.toString(o).replaceAll("]", "").substring(1));
        for (int key : hashMap.keySet()) {
            if (key < id) {
                keysForRemoving.add(key);
            }
        }
        for (int key : keysForRemoving) {
            hashMap.remove(key);
            result += "Из коллекции был удалён эменет с id = " + key + "\n";
        }
        return new CommandResult(result, true);
    }


    @Override
    public String getDescr() {
        return "Удалить из коллекции все элементы, ключ которых меньше, чем заданный.\n" +
                "Синтаксис: remove_lower_key {id}";
    }
}
