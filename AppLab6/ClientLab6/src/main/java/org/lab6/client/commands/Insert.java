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
    public CommandResult execute(Object... o) {
        List<Integer> a = new ArrayList<>();
        for (Integer key : hashMap.keySet()) {
            a.add(hashMap.get(key).getId());
        }

        RandomNum randomNum = new RandomNum();
        int randomInt = randomNum.createRandomNum(hashMap);

        CreationModel creationModel = new CreationModel();
        hashMap.put(randomInt, creationModel.createModel(randomInt));

        return new CommandResult("Модель с id = " + randomInt + " успешно добавлена!", true);
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
