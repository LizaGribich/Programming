package org.lab6.server.commands;

import org.lab6.server.*;
import org.lab6.server.models.Vehicle;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExecuteScript implements Comandable {
    static String name = "execute_script";
    private MapWrapper<Integer, Vehicle> hashMap;
    static List<String> usedFiles = new ArrayList<>();

    public ExecuteScript(MapWrapper<Integer, Vehicle> hashMap) {
        this.hashMap = hashMap;
    }

    @Override
    public CommandResult execute(Object... o) throws IOException {
        CommandResult commandResult;

        String f = Arrays.toString(o).replaceAll("]", "").substring(1);
        if (usedFiles.contains(f)) {
            return new CommandResult("Обнаружена рекурсия.", false);
        }
        usedFiles.add(f);
        try (BufferedReader reader = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] arrayOfInput = line.split(" ");
                CommandManager commandManager = new CommandManager(hashMap);
                commandManager.makeCollectionOfCommands();
                InteractiveMode.runScript(commandManager.getCommands(),hashMap, arrayOfInput);
            }
        } catch (FileNotFoundException e) {
            commandResult = new CommandResult("Файл не найден.", false);
        } catch (IOException e) {
            commandResult = new CommandResult("Ошибка доступа", false);
        } catch (Exception e) {
            commandResult = new CommandResult("jcnsjsc", false);
        }
        usedFiles.clear();
        String res = InteractiveMode.returnScriptRes();
        System.out.println(res);
        return new CommandResult(res, true);
    }



    @Override
    public String getDescr() {
        return "Выполнить скрипт из введённого файла.\n" +
                "Синтаксис: execute_script file";
    }

    public static String getName() {
        return name;
    }
}
