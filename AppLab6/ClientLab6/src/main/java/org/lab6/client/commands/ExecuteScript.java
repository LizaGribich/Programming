package org.lab6.client.commands;

import org.lab6.client.*;
import org.lab6.client.models.Vehicle;


public class ExecuteScript implements Comandable {
    static String name = "execute_script";
    private MapWrapper<Integer, Vehicle> hashMap;

    public ExecuteScript(MapWrapper<Integer, Vehicle> hashMap) {
        this.hashMap = hashMap;
    }


    @Override
    public String getDescr() {
        return "Выполнить скрипт из введённого файла.\n" +
                "Синтаксис: execute_script file";
    }

    public static String getName() {
        return name;
    }
}
