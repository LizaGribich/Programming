package org.lab6.client.commands;

import org.lab6.client.*;



public class History implements Comandable {
    static String name = "history";
    public static String getName(){
        return name;
    }

    @Override
    public String getDescr() {
        return "Выводит последние 13 введённых пользователем команд.\n" +
                "Синтаксис: history";
    }

}
