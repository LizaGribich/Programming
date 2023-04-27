package org.lab5;

import org.lab5.commands.*;
import org.lab5.models.Vehicle;

import java.util.HashMap;

public class CommandManager {
    private HashMap<String, Comandable> commandsMap = new HashMap<>();
    private MapWrapper<Integer, Vehicle> hashMap = new MapWrapper<>(HashMap.class);
    public CommandManager (MapWrapper<Integer, Vehicle> hashMap) throws Exception {
        this.hashMap = hashMap;
    }


    public void makeCollectionOfCommands() throws Exception {
        commandsMap.put(Clear.getName(), new Clear(hashMap));
        commandsMap.put(RemoveKey.getName(), new RemoveKey(hashMap));
        commandsMap.put(Show.getName(), new Show(hashMap));
        commandsMap.put(Help.getName(), new Help(hashMap));
        commandsMap.put(RemoveLowerKey.getName(), new RemoveLowerKey(hashMap));
        commandsMap.put(SumOfEnginePower.getName(), new SumOfEnginePower(hashMap));
        commandsMap.put(PrintFieldDescendingEnginePower.getName(), new PrintFieldDescendingEnginePower(hashMap));
        commandsMap.put(Exit.getName(), new Exit());
        commandsMap.put(Update.getName(), new Update(hashMap));
        commandsMap.put(Insert.getName(), new Insert(hashMap));
        commandsMap.put(PrintUniqueCapacity.getName(), new PrintUniqueCapacity(hashMap));
        commandsMap.put(ReplaceIfGreater.getName(), new ReplaceIfGreater(hashMap));
        commandsMap.put(History.getName(), new History());
        commandsMap.put(Info.getName(), new Info(hashMap));
        commandsMap.put(Save.getName(), new Save(hashMap));
        commandsMap.put(ExecuteScript.getName(), new ExecuteScript(hashMap));
    }

    public void removeExecuteScript() {
        commandsMap.remove(ExecuteScript.getName());
        this.commandsMap = commandsMap;
    }

    public HashMap<String, Comandable> getCommands(){
        return commandsMap;
    }


}
