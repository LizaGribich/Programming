package org.lab6.client;

import org.lab6.client.commands.*;

import java.util.ArrayList;

public class CommandManager {
    private ArrayList<String> commandsMap = new ArrayList<>();
    private ArrayList<String> commandsExtraData = new ArrayList<>();
    private ArrayList<String> commandsExtraModel = new ArrayList<>();
    private ArrayList<String> commandsIntArgument = new ArrayList<>();
    private ArrayList<String> commandsStringArgument = new ArrayList<>();

    public CommandManager() {}


    public void makeCollectionOfCommands() throws Exception {
        commandsMap.add(Clear.getName());
        commandsMap.add(RemoveKey.getName());
        commandsIntArgument.add(RemoveKey.getName());
        commandsMap.add(Show.getName());
        commandsMap.add(Help.getName());
        commandsMap.add(RemoveLowerKey.getName());
        commandsIntArgument.add(RemoveLowerKey.getName());
        commandsMap.add(SumOfEnginePower.getName());
        commandsMap.add(PrintFieldDescendingEnginePower.getName());
        commandsMap.add(Exit.getName());
        commandsMap.add(Update.getName());
        commandsIntArgument.add(Update.getName());
        commandsExtraModel.add(Update.getName());
        commandsMap.add(Insert.getName());
        commandsExtraModel.add(Insert.getName());
        commandsMap.add(PrintUniqueCapacity.getName());
        commandsMap.add(ReplaceIfGreater.getName());
        commandsIntArgument.add(ReplaceIfGreater.getName());
        commandsExtraData.add(ReplaceIfGreater.getName());
        commandsMap.add(History.getName());
        commandsMap.add(Info.getName());
        commandsMap.add(Save.getName());
        commandsMap.add(ExecuteScript.getName());
        commandsStringArgument.add(ExecuteScript.getName());
    }

    public void removeExecuteScript() {
        commandsMap.remove(ExecuteScript.getName());
        this.commandsMap = commandsMap;
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
    public ArrayList<String> getCommandsIntArgument() {
        return commandsIntArgument;
    }
    public ArrayList<String> getCommandsStringArgument() {
        return commandsStringArgument;
    }

}
