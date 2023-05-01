package org.lab6.server.commands;

import org.lab6.server.*;
import org.lab6.server.models.Vehicle;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

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
    public CommandResult execute(Object... o) {
        HashMap<Integer, Double> unsortedList = new HashMap<>();
        for (int key : hashMap.keySet()) {
            unsortedList.put(key, hashMap.get(key).getEnginePower());
        }

        Map<Integer, Double> sortedMap = unsortedList.entrySet().stream()
                .sorted(Comparator.comparingInt(e -> (int) -e.getValue()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> { throw new AssertionError(); },
                        LinkedHashMap::new
                ));

        return new CommandResult(sortedMap.entrySet().toString(), true);
    }



    @Override
    public String getDescr() {
        return "Вывести значения поля enginePower всех элементов в порядке убывания.\n" +
                "Синтаксис: print_field_descending_engine_power";
    }
}
