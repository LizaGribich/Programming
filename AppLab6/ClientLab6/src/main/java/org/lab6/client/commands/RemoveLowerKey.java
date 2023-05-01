package org.lab6.client.commands;

import org.lab6.client.*;
import org.lab6.client.models.Vehicle;

public class RemoveLowerKey implements Comandable {
    static String name = "remove_lower_key";
    private MapWrapper<Integer, Vehicle> hashMap;
    public RemoveLowerKey (MapWrapper<Integer, Vehicle> hashMap) {
        this.hashMap = hashMap;
    }

    public static String getName() {
        return name;
    }


    @Override
    public String getDescr() {
        return "Удалить из коллекции все элементы, ключ которых меньше, чем заданный.\n" +
                "Синтаксис: remove_lower_key {id}";
    }
}
