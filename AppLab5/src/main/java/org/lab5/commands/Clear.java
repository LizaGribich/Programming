package org.lab5.commands;

import org.lab5.CommandResult;
import org.lab5.MapWrapper;
import org.lab5.models.Vehicle;
import org.lab5.Comandable;

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
    public CommandResult execute(Object... id) {
        hashMap.clear();
        return new CommandResult("Коллекция очищена.", true);
    }

    @Override
    public String getDescr() {
        return "Очистить коллекцию.\n" +
                "Синтаксис: clear";
    }


}
