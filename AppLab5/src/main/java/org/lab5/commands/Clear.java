package org.lab5.commands;

import org.lab5.models.Vehicle;
import org.lab5.Comandable;

import java.util.HashMap;

public class Clear implements Comandable {
    static String name = "clear";
    private HashMap<Integer, Vehicle> hashMap = new HashMap<>();
    public Clear (HashMap<Integer, Vehicle> hashMap) {
        this.hashMap = hashMap;
    }

    public static String getName() {
        return name;
    }

    @Override
    public void execute( Object... id) {
        hashMap.clear();
        System.out.println("Коллекция очищена.");
    }

    @Override
    public String getDescr() {
        return "Очистить коллекцию.\n" +
                "Синтаксис: clear";
    }


}
