package org.lab6.client.commands;

import org.lab6.client.*;
import org.lab6.client.models.Vehicle;

import java.util.Arrays;

public class RemoveKey implements Comandable {

    static String name = "remove_key";
    private MapWrapper<Integer, Vehicle> hashMap;
    public RemoveKey (MapWrapper<Integer, Vehicle> hashMap) {
        this.hashMap = hashMap;
    }

    public static String getName() {
        return name;
    }


    @Override
    public String getDescr() {
        return "Удалить элемент из коллекции по его ключу.\n" +
                "Синтаксис: remove_key {id}";
    }


}
