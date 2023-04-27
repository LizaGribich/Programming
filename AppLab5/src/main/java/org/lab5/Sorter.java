package org.lab5;

import java.util.Comparator;
import java.util.Map;

import org.lab5.models.Vehicle;

public class Sorter {
    public void sortByEnginePower(MapWrapper<Integer, Vehicle> hashMap) {
        ConsolePrinter consolePrinter = new ConsolePrinter();
        hashMap.entrySet()
                .stream()
                .sorted(Map.Entry.<Integer, Vehicle>comparingByValue(Comparator.reverseOrder()))
                .forEach(entry -> consolePrinter.printToConsole(entry.getKey() + " : " + entry.getValue().toString()));
    }
}
