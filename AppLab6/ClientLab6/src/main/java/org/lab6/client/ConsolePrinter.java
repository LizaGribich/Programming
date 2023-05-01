package org.lab6.client;

public class ConsolePrinter {
    public void printResToConsole(CommandResult commandResult) {
        System.out.println(commandResult.getMessage());
    }
    public void printToConsole(String message) {
        System.out.println(message);
    }
}
