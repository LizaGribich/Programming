package org.lab5.commands;

import org.lab5.Comandable;
import org.lab5.InteractiveMode;
import org.lab5.models.Vehicle;

import java.util.HashMap;

public class History implements Comandable {
    static String name = "history";
    public static String getName(){
        return name;
    }

    @Override
    public void execute(HashMap<Integer, Vehicle> hashMap, Object... id) {
        InteractiveMode.getDeque();
        System.out.println(InteractiveMode.getDeque());
    }
    @Override
    public String getDescr() {
        return "Выводит последние 13 введённых пользователем команд.\n" +
                "Синтаксис: history";
    }

}
