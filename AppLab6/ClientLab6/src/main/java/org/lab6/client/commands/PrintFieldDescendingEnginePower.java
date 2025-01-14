package org.lab6.client.commands;

import org.lab6.client.*;
import org.lab6.client.models.Vehicle;


public class PrintFieldDescendingEnginePower implements Comandable {
    static String name = "print_field_descending_engine_power";
    private MapWrapper<Integer, Vehicle> hashMap;
    public PrintFieldDescendingEnginePower (MapWrapper<Integer, Vehicle> hashMap) {
        this.hashMap = hashMap;
    }

    public static String getName() {
        return name;
    }


    @Override
    public String getDescr() {
        return "Вывести значения поля enginePower всех элементов в порядке убывания.\n" +
                "Синтаксис: print_field_descending_engine_power";
    }
}
