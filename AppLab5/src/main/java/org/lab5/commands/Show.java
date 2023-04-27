package org.lab5.commands;

import org.lab5.Comandable;
import org.lab5.CommandResult;
import org.lab5.MapWrapper;
import org.lab5.Sorter;
import org.lab5.models.Vehicle;


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
