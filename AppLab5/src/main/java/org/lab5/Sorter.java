package org.lab5;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import org.lab5.models.Vehicle;

public class Sorter {
    public void sortByEnginePower(HashMap<Integer, Vehicle> hashMap) {
        hashMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(System.out::println);
    }
}
