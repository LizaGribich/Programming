package org.lab6.client.commands;

import org.lab6.client.*;
import org.lab6.client.models.Vehicle;


public class Save implements Comandable {
    static String name = "save";
    private MapWrapper<Integer, Vehicle> hashMap;
    public Save (MapWrapper<Integer, Vehicle> hashMap) {
        this.hashMap = hashMap;
    }

    public static String getName() {
        return name;
    }

    @Override
    public String getDescr() {
        return "Сохранить коллекцию в файл.\n" +
                "Синтаксис: save";
    }

}
