package org.lab5.commands;

import org.lab5.Comandable;
import org.lab5.models.Vehicle;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

public class PrintUniqueCapacity implements Comandable {
    static String name = "print_unique_capacity";
    private HashMap<Integer, Vehicle> hashMap = new HashMap<>();
    public PrintUniqueCapacity (HashMap<Integer, Vehicle> hashMap) {
        this.hashMap = hashMap;
    }


    @Override
    public void execute(Object... o) {
        HashMap<Integer, Float> CapacityList = new HashMap<>();
        for (int key : hashMap.keySet()) {
            CapacityList.put(key, hashMap.get(key).getCapacity());
        }

        Set<Float> capacityValues = new TreeSet<>(CapacityList.values());
        capacityValues.forEach(System.out::println);
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
