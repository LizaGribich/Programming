package org.lab6.client.commands;

import org.lab6.client.*;
import org.lab6.client.models.Vehicle;


public class PrintUniqueCapacity implements Comandable {
    static String name = "print_unique_capacity";
    private MapWrapper<Integer, Vehicle> hashMap;
    public PrintUniqueCapacity (MapWrapper<Integer, Vehicle> hashMap) {
        this.hashMap = hashMap;
    }



    @Override
    public String getDescr() {
        return "Вывести уникальные значения поля capacity всех элементов в коллекции.\n" +
                "Синтаксис: print_unique_capacity";
    }

    public static String getName() {
        return name;
    }
}
