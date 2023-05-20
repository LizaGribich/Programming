package org.lab7.server.commands;

import org.lab7.server.*;
import org.lab7.server.models.Vehicle;

import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

public class PrintUniqueCapacity implements Comandable {
    static String name = "print_unique_capacity";
    private MapWrapper<Integer, Vehicle> hashMap;
    public PrintUniqueCapacity (MapWrapper<Integer, Vehicle> hashMap) {
        this.hashMap = hashMap;
    }


    @Override
    public CommandResult execute(Object... o) {
        HashMap<Integer, Float> CapacityList = new HashMap<>();
        for (int key : hashMap.keySet()) {
            CapacityList.put(key, hashMap.get(key).getCapacity());
        }

        Set<Float> capacityValues = new TreeSet<>(CapacityList.values());
        return new CommandResult(capacityValues.toString(), true);
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
