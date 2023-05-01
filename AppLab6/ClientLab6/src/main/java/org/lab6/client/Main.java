package org.lab6.client;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws Exception {
        try {
            CommandManager commandManager = new CommandManager();
            commandManager.makeCollectionOfCommands();
            InteractiveMode.interactiveModeOn(commandManager.getCommands());
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден.");
        }
    }
}
