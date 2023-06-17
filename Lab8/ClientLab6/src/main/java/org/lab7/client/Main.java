package org.lab7.client;

public class Main {
    public static void main(String[] args) throws Exception {

        CommandManager commandManager = new CommandManager();
        commandManager.makeCollectionOfCommands();
        InteractiveMode.interactiveModeOn(commandManager.getCommands());

    }
}
