package org.lab5.commands;

import org.lab5.*;
import org.lab5.models.Vehicle;


public class Help implements Comandable {
    static String name = "help";
    private MapWrapper<Integer, Vehicle> hashMap;
    public Help (MapWrapper<Integer, Vehicle> hashMap) {
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
    public CommandResult execute(Object... o) throws Exception {
        ConsolePrinter consolePrinter = new ConsolePrinter();
        consolePrinter.printToConsole("Доступные команды:");

        CommandManager commandManager = new CommandManager(hashMap);
        commandManager.makeCollectionOfCommands();
        String descr = "";

        for (String key : commandManager.getCommands().keySet()) {
            descr += commandManager.getCommands().get(key).getDescr() + "\n\n";
        }
        return new CommandResult(descr, true);
    }
}
