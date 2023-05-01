package org.lab6.server.commands;

import org.lab6.server.*;
import org.lab6.server.models.Vehicle;


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
        String res = sorter.sortByEnginePower(hashMap);
        return new CommandResult(res + "\nКоллекция успешно выведена.", true);
    }


    @Override
    public String getDescr() {
        return "Вывести в стандартный поток вывода все элементы коллекции в строковом представлении.\n" +
                "Синтаксис: show";
    }
}
