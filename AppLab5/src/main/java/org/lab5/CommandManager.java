package org.lab5;

import org.lab5.commands.*;

import java.util.HashMap;

public class CommandManager {
    private HashMap<String, Comandable> commandsMap = new HashMap<>();;


    public void makeCollectionOfCommands() {
        commandsMap.put(Clear.getName(), new Clear());
        commandsMap.put(RemoveKey.getName(), new RemoveKey());
        commandsMap.put(Show.getName(), new Show());
        commandsMap.put(Help.getName(), new Help());
        commandsMap.put(RemoveLowerKey.getName(), new RemoveLowerKey());
        commandsMap.put(SumOfEnginePower.getName(), new SumOfEnginePower());
        commandsMap.put(PrintFieldDescendingEnginePower.getName(), new PrintFieldDescendingEnginePower());
        commandsMap.put(Exit.getName(), new Exit());
        commandsMap.put(Update.getName(), new Update());
        commandsMap.put(Insert.getName(), new Insert());
        commandsMap.put(PrintUniqueCapacity.getName(), new PrintUniqueCapacity());
        commandsMap.put(ReplaceIfGreater.getName(), new ReplaceIfGreater());
        commandsMap.put(History.getName(), new History());
        commandsMap.put(Info.getName(), new Info());
        commandsMap.put(Save.getName(), new Save());
        commandsMap.put(ExecuteScript.getName(), new ExecuteScript());
    }

    public void removeExecuteScript() {
        commandsMap.remove(ExecuteScript.getName());
        this.commandsMap = commandsMap;
    }

    public HashMap<String, Comandable> getCommands(){
        return commandsMap;
    }


}
