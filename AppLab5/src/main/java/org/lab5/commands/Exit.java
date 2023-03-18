package org.lab5.commands;

import org.lab5.Comandable;
import org.lab5.InteractiveMode;
import org.lab5.models.Vehicle;

import java.util.HashMap;

public class Exit implements Comandable {
    static String name = "exit";

    public static String getName() {
        return name;
    }
    @Override
    public void execute(HashMap<Integer, Vehicle> hashMap, Object... id) {
        InteractiveMode.InteractiveModeOff();
    }
    @Override

    public String getDescr() {
        return "Завершает выполнение программы.\n" +
                "Синтаксис: exit";
    }
}
