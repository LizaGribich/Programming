package org.lab7.server;


import org.lab7.server.models.Vehicle;


public class Main {

    public static void main(String[] args) throws Exception {

        MapWrapper<Integer, Vehicle> vehicleCollection = Database.readBase();
        CommandManager commandManager = new CommandManager(vehicleCollection);
        commandManager.makeCollectionOfCommands();

        InteractiveMode.interactiveModeOn(commandManager.getCommands(), vehicleCollection);

    }
}
