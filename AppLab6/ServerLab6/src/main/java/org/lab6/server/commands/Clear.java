package org.lab6.server.commands;

import org.lab6.server.*;
import org.lab6.server.models.Vehicle;

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
