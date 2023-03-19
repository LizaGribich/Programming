package org.lab5.commands;

import org.lab5.Comandable;
import org.lab5.models.Vehicle;

import java.io.IOException;
import java.text.ParseException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class PrintFieldDescendingEnginePower implements Comandable {
    static String name = "print_field_descending_engine_power";
    private HashMap<Integer, Vehicle> hashMap = new HashMap<>();
    public PrintFieldDescendingEnginePower (HashMap<Integer, Vehicle> hashMap) {
        this.hashMap = hashMap;
    }

    public static String getName() {
        return name;
    }


    @Override
    public void execute(Object... o) {
        HashMap<Integer, Double> UnsortedList = new HashMap<>();
        for (int key : hashMap.keySet()) {
            UnsortedList.put(key, hashMap.get(key).getEnginePower());
        }

        UnsortedList.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(System.out::println);
    }

    @Override
    public String getDescr() {
        return "Вывести значения поля enginePower всех элементов в порядке убывания.\n" +
                "Синтаксис: print_field_descending_engine_power";
    }
}
