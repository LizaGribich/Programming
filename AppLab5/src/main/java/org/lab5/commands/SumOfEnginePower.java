package org.lab5.commands;

import org.lab5.Comandable;
import org.lab5.CommandResult;
import org.lab5.MapWrapper;
import org.lab5.models.Vehicle;

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
    public CommandResult execute(Object... o) {
        Double summa = 0.0;
        for (Vehicle value : hashMap.values()) {
            summa += value.getEnginePower();
        }
        return new CommandResult("Сумма значений поля EnginePower для всех элементов коллеции = " + summa, true);
    }

    @Override
    public String getDescr() {
        return "Вывести сумму значений поля enginePower для всех элементов коллекции.\n" +
                "Синтаксис: sum_of_engine_power";
    }
}
