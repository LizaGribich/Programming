package org.lab6.server.commands;

import org.lab6.server.*;
import org.lab6.server.models.Vehicle;

import java.util.Arrays;

public class RemoveKey implements Comandable {

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
        int id = Integer.valueOf(Arrays.toString(o).replaceAll("]", "").substring(1));
        CommandResult commandResult;
        if (hashMap.get(id) != null) {
            hashMap.remove(id);
            commandResult = new CommandResult("Из коллекции удалён элемент с id = " + id + ".", true);
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
