package org.lab5.commands;

import org.lab5.CreationModel;
import org.lab5.Comandable;
import org.lab5.RandomNum;
import org.lab5.models.Vehicle;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Insert implements Comandable {
    static String name = "insert";
    private HashMap<Integer, Vehicle> hashMap = new HashMap<>();
    public Insert (HashMap<Integer, Vehicle> hashMap) {
        this.hashMap = hashMap;
    }

    @Override
    public void execute(Object... o) {
        List<Integer> a = new ArrayList<>();
        for (Integer key : hashMap.keySet()) {
            a.add(hashMap.get(key).getId());
        }

        RandomNum randomNum = new RandomNum();
        int randomInt = randomNum.createRundomNum(a);

        CreationModel creationModel = new CreationModel();
        hashMap.put(randomInt, creationModel.createModel(hashMap, randomInt));

        System.out.println("Модель с id = " + randomInt + " успешно добавлена!");
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
