package org.lab5.commands;

import org.lab5.Comandable;
import org.lab5.CommandResult;
import org.lab5.InteractiveMode;


public class Exit implements Comandable {
    static String name = "exit";

    public static String getName() {
        return name;
    }
    @Override
    public CommandResult execute(Object... id) {
        InteractiveMode.InteractiveModeOff();
        return new CommandResult("Программа завершена!", true);
    }
    @Override
    public String getDescr() {
        return "Завершает выполнение программы.\n" +
                "Синтаксис: exit";
    }
}
