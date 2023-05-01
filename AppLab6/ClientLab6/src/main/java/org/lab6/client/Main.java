package org.lab6.client;


public class Main {
    public static void main(String[] args){

        CommandManager commandManager = new CommandManager();
        commandManager.makeCollectionOfCommands();
        InteractiveMode.interactiveModeOn(commandManager.getCommands());

    }
}
