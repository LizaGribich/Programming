package org.lab7.server.commands;

import org.lab7.server.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;

public class History implements Comandable {
    static String name = "history";
    public static String getName(){
        return name;
    }

    @Override
    public CommandResult execute(Object... o) {

        String inputString = Arrays.toString(o);
        String[] inputValues = inputString.substring(1, inputString.length() - 1).split(" ");
        String userName = inputValues[1];
        return new CommandResult(Arrays.toString(new Collection[]{InteractiveMode.getDeque().get(userName)}), true);
    }


    @Override
    public String getDescr() {
        return "Выводит последние 13 введённых пользователем команд.\n" +
                "Синтаксис: history";
    }

}
