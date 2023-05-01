package org.lab6.client.commands;

import org.lab6.client.*;



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
