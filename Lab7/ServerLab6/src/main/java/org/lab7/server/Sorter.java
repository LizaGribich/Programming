package org.lab7.server;


import org.lab7.server.models.Vehicle;

import java.util.Comparator;
import java.util.Map;

public class Sorter {

    public String sortByEnginePower(MapWrapper<Integer, Vehicle> hashMap) {
        StringBuilder resultBuilder = new StringBuilder();
        hashMap.entrySet()
                .stream()
                .sorted(Map.Entry.<Integer, Vehicle>comparingByValue(Comparator.reverseOrder()))
                .forEach(entry -> resultBuilder.append(entry.getKey()).append(" : ").append(entry.getValue().toString()).append(System.lineSeparator()));

        String result = resultBuilder.toString();
        return result;
    }
}
