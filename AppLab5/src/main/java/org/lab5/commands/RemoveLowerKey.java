package org.lab5.commands;

import org.lab5.Comandable;
import org.lab5.models.Vehicle;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class RemoveLowerKey implements Comandable {
    static String name = "remove_lower_key";

    public static String getName() {
        return name;
    }

    @Override
    public void execute(HashMap<Integer, Vehicle> hashMap, Object... o) throws ParseException, IOException {
        List<Integer> keysForRemoving = new ArrayList<Integer>();
        int id = Integer.valueOf(Arrays.toString(o).replaceAll("]", "").substring(1));
        for (int key : hashMap.keySet()) {
            if (key < id) {
                keysForRemoving.add(key);
            }
        }
        for (int key : keysForRemoving) {
            hashMap.remove(key);
            System.out.println("Из коллекции был удалён эменет с id = " + key);
        }
    }

    @Override
    public String getDescr() {
        return "Удалить из коллекции все элементы, ключ которых меньше, чем заданный.\n" +
                "Синтаксис: remove_lower_key {id}";
    }
}
