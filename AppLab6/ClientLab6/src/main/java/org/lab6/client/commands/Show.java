package org.lab6.client.commands;

import org.lab6.client.*;
import org.lab6.client.models.Vehicle;


public class Show implements Comandable {
    static String name = "show";
    private MapWrapper<Integer, Vehicle> hashMap;
    public Show (MapWrapper<Integer, Vehicle> hashMap) {
        this.hashMap = hashMap;
    }
    public static String getName() {
        return name;
    }

    @Override
    public CommandResult execute(Object... id) {
        Sorter sorter = new Sorter();
        sorter.sortByEnginePower(hashMap);
        return new CommandResult("Коллекция успешно выведена.", true);
    }

    @Override
    public String getDescr() {
        return "Вывести в стандартный поток вывода все элементы коллекции в строковом представлении.\n" +
                "Синтаксис: show";
    }
}
