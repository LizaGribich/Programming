package org.lab6.client.commands;

import org.lab6.client.*;
import org.lab6.client.models.Vehicle;

public class SumOfEnginePower implements Comandable {
    static String name = "sum_of_engine_power";
    private MapWrapper<Integer, Vehicle> hashMap;
    public SumOfEnginePower (MapWrapper<Integer, Vehicle> hashMap) {
        this.hashMap = hashMap;
    }

    public static String getName() {
        return name;
    }


    @Override
    public String getDescr() {
        return "Вывести сумму значений поля enginePower для всех элементов коллекции.\n" +
                "Синтаксис: sum_of_engine_power";
    }
}
