package org.lab5.commands;

import org.lab5.models.Vehicle;
import org.lab5.CommandManager;
import org.lab5.Comandable;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;


public class Help implements Comandable {
    static String name = "help";

    public static String getName() {
        return name;
    }

    @Override
    public String getDescr() {
        return "Выводит информацию о доступных командах.\n" +
                "Синтаксис: help";
    }

    @Override
    public void execute(HashMap<Integer, Vehicle> hashMap, Object... o) throws ParseException, IOException {
        System.out.println("Доступные команды:");

        CommandManager commandManager = new CommandManager();
        commandManager.makeCollectionOfCommands();

        for (String key : commandManager.getCommands().keySet()) {
            System.out.println(commandManager.getCommands().get(key).getDescr());
            System.out.println();
        }
    }
}
