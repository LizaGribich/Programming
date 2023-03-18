package org.lab5.commands;

import org.lab5.CommandManager;
import org.lab5.Comandable;
import org.lab5.InteractiveMode;
import org.lab5.models.Vehicle;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.HashMap;

public class ExecuteScript implements Comandable {
    static String name = "execute_script";

    @Override
    public void execute(HashMap<Integer, Vehicle> hashMap, Object... o) {

        String f = Arrays.toString(o).replaceAll("]", "").substring(1);

        try (BufferedReader reader = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] ArrayOfInput = line.split(" ");
                CommandManager commandManager = new CommandManager();
                commandManager.makeCollectionOfCommands();
                commandManager.removeExecuteScript();
                InteractiveMode.runCommand(commandManager.getCommands(), hashMap, ArrayOfInput);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден.");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
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
