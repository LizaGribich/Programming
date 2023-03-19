package org.lab5.commands;

import org.lab5.Comandable;
import org.lab5.models.Vehicle;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.HashMap;

public class RemoveKey implements Comandable {

    static String name = "remove_key";
    private HashMap<Integer, Vehicle> hashMap = new HashMap<>();
    public RemoveKey (HashMap<Integer, Vehicle> hashMap) {
        this.hashMap = hashMap;
    }

    public static String getName() {
        return name;
    }

    @Override
    public void execute(Object... o) {
        int id = Integer.valueOf(Arrays.toString(o).replaceAll("]", "").substring(1));
        if (hashMap.get(id) != null) {
            hashMap.remove(id);
            System.out.println("Из коллекции удалён элемент с id = " + id + ".");
        } else {
            System.out.println("Модель с id = " + id + " не существует!");
        }

    }

    @Override
    public String getDescr() {
        return "Удалить элемент из коллекции по его ключу.\n" +
                "Синтаксис: remove_key {id}";
    }


}
