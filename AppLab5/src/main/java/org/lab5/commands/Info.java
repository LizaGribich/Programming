package org.lab5.commands;

import org.lab5.Comandable;
import org.lab5.CommandResult;
import org.lab5.MapWrapper;
import org.lab5.models.Vehicle;

public class Info implements Comandable {
    static String name = "info";
    private MapWrapper<Integer, Vehicle> hashMap;
    public Info (MapWrapper<Integer, Vehicle> hashMap) {
        this.hashMap = hashMap;
    }

    @Override
    public CommandResult execute(Object... o) {
        return new CommandResult("Тип коллеции: " + hashMap.getClass() + "\n"
                + "Колличество элементов: " + hashMap.size()+ "\n", true);
    }

    @Override
    public String getDescr() {
        return "Выводит информацию о коллекции.\n" +
                "Синтаксис: info";
    }

    public static String getName() {
        return name;
    }
}
