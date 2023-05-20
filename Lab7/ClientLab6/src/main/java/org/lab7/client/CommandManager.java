package org.lab7.client;

import org.lab7.client.commands.*;

import java.util.ArrayList;

public class CommandManager {
    private ArrayList<String> commandsMap = new ArrayList<>();
    private ArrayList<String> commandsExtraData = new ArrayList<>();
    private ArrayList<String> commandsExtraModel = new ArrayList<>();

    public CommandManager() {}


    public void makeCollectionOfCommands(){
        commandsMap.add(Clear.getName());
        commandsMap.add(RemoveKey.getName());
        commandsMap.add(Show.getName());
        commandsMap.add(Help.getName());
        commandsMap.add(RemoveLowerKey.getName());
        commandsMap.add(SumOfEnginePower.getName());
        commandsMap.add(PrintFieldDescendingEnginePower.getName());
        commandsMap.add(Exit.getName());
        commandsMap.add(Update.getName());
        commandsExtraModel.add(Update.getName());
        commandsMap.add(Insert.getName());
        commandsExtraModel.add(Insert.getName());
        commandsMap.add(PrintUniqueCapacity.getName());
        commandsMap.add(ReplaceIfGreater.getName());
        commandsExtraData.add(ReplaceIfGreater.getName());
        commandsMap.add(History.getName());
        commandsMap.add(Info.getName());
        commandsMap.add(Save.getName());
        commandsMap.add(ExecuteScript.getName());
    }


    public ArrayList<String> getCommands(){
        return commandsMap;
    }
    public ArrayList<String> getCommandsExtraData() {
        return commandsExtraData;
    }

    public ArrayList<String> getCommandsExtraModel() {
        return commandsExtraModel;
    }

}
