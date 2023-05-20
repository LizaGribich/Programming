package org.lab7.server.commands;

import org.lab7.server.*;

import java.util.Arrays;
import java.util.Deque;

public class History implements Comandable {
    static String name = "history";
    public static String getName(){
        return name;
    }

    @Override
    public CommandResult execute(Object... id) {
        return new CommandResult(Arrays.toString(new Deque[]{InteractiveMode.getDeque()}), true);
    }


    @Override
    public String getDescr() {
        return "Выводит последние 13 введённых пользователем команд.\n" +
                "Синтаксис: history";
    }

}
