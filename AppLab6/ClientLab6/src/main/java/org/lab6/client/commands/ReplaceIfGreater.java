package org.lab6.client.commands;

import org.lab6.client.*;
import org.lab6.client.models.Vehicle;


public class ReplaceIfGreater implements Comandable {
    static String name = "replace_if_greater";
    private MapWrapper<Integer, Vehicle> hashMap;
    public ReplaceIfGreater (MapWrapper<Integer, Vehicle> hashMap) throws Exception {
        this.hashMap = hashMap;
    }

    @Override
    public String getDescr() {
        return "Заменить значение по ключу, если новое значение больше старого.\n" +
                "Синтаксис: replace_if_greater {id}";
    }
    public static String getName() {
        return name;
    }
}
