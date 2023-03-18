package org.lab5.commands;

import org.lab5.Comandable;
import org.lab5.Sorter;
import org.lab5.models.Vehicle;

import java.util.HashMap;

public class Show implements Comandable {
    static String name = "show";
    public static String getName() {
        return name;
    }

    @Override
    public void execute(HashMap<Integer, Vehicle> hashMap, Object... id) {
        Sorter sorter = new Sorter();
        sorter.sortByEnginePower(hashMap);
    }

    @Override
    public String getDescr() {
        return "Вывести в стандартный поток вывода все элементы коллекции в строковом представлении.\n" +
                "Синтаксис: show";
    }
}
