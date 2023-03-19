package org.lab5.commands;

import org.lab5.Comandable;
import org.lab5.models.Vehicle;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;

public class Info implements Comandable {
    static String name = "info";
    private HashMap<Integer, Vehicle> hashMap = new HashMap<>();
    public Info (HashMap<Integer, Vehicle> hashMap) {
        this.hashMap = hashMap;
    }

    @Override
    public void execute(Object... o) {
        System.out.println("Тип коллеции: " + hashMap.getClass());
        System.out.println("Колличество элементов: " + hashMap.size());
    }

    @Override
    public String getDescr() {
        return "Выводит информацию о коллекции.\n" +
                "Синтаксис: info";
    }

    public static String getName() {
        return name;
    }
}
