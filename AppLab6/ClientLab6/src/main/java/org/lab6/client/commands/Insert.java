package org.lab6.client.commands;

import org.lab6.client.*;
import org.lab6.client.models.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class Insert implements Comandable {
    static String name = "insert";
    private MapWrapper<Integer, Vehicle> hashMap;
    public Insert (MapWrapper<Integer, Vehicle> hashMap) {
        this.hashMap = hashMap;
    }

    @Override
    public String getDescr() {
        return "Добавить новый элемент с автоматически сгенерированным ключом.\n" +
                "Синтаксис: insert";
    }

    public static String getName() {
        return name;
    }
}
