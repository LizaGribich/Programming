package org.lab6.client.commands;

import org.lab6.client.*;
import org.lab6.client.models.Vehicle;

public class Info implements Comandable {
    static String name = "info";
    private MapWrapper<Integer, Vehicle> hashMap;
    public Info (MapWrapper<Integer, Vehicle> hashMap) {
        this.hashMap = hashMap;
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
