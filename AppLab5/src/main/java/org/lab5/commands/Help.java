package org.lab5.commands;

import org.lab5.models.Vehicle;
import org.lab5.CommandManager;
import org.lab5.Comandable;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;


public class Help implements Comandable {
    static String name = "help";
    private HashMap<Integer, Vehicle> hashMap = new HashMap<>();
    public Help (HashMap<Integer, Vehicle> hashMap) {
        this.hashMap = hashMap;
    }

    public static String getName() {
        return name;
    }

    @Override
    public String getDescr() {
        return "Выводит информацию о доступных командах.\n" +
                "Синтаксис: help";
    }

    @Override
    public void execute(Object... o) {
        System.out.println("Доступные команды:");

        CommandManager commandManager = new CommandManager(hashMap);
        commandManager.makeCollectionOfCommands();

        for (String key : commandManager.getCommands().keySet()) {
            System.out.println(commandManager.getCommands().get(key).getDescr());
            System.out.println();
        }
    }
}
