package org.lab6.client.commands;

import org.lab6.client.*;
import org.lab6.client.models.Vehicle;

import java.util.Arrays;
import java.util.Scanner;

public class ReplaceIfGreater implements Comandable {
    static String name = "replace_if_greater";
    private MapWrapper<Integer, Vehicle> hashMap;
    public ReplaceIfGreater (MapWrapper<Integer, Vehicle> hashMap) throws Exception {
        this.hashMap = hashMap;
    }

    @Override
    public CommandResult execute(Object... o) {
        ConsolePrinter consolePrinter = new ConsolePrinter();
        int id = Integer.valueOf(Arrays.toString(o).replaceAll("]", "").substring(1));
        CommandResult commandResult;
        if (hashMap.get(id) != null) {
            Scanner scanner = new Scanner(System.in);
            consolePrinter.printToConsole("Введите название поля, которое хотите заменить (enginePower/capacity):");
            String name = scanner.nextLine();
            if (name.equals("enginePower")) {
                consolePrinter.printToConsole("Введите новое значение поля:");
                Double enginePower = scanner.nextDouble();
                if (hashMap.get(id).getEnginePower() - enginePower < 0) {
                    hashMap.get(id).setEnginePower(enginePower);
                    commandResult = new CommandResult("Значение enginePower успешно изменено!", true);
                } else {
                    commandResult = new CommandResult("Введённое значение" +
                            " не больше уже установленного.\nЗначение enginePower не изменено.", false);
                }
            } else if (name.equals("capacity")) {
                consolePrinter.printToConsole("Введите новое значение поля:");
                float capacity = scanner.nextFloat();
                if (hashMap.get(id).getCapacity() - capacity < 0) {
                    hashMap.get(id).setCapacity(capacity);
                    commandResult = new CommandResult("Значение capacity успешно изменено!", true);
                } else {
                    commandResult = new CommandResult("Введённое значение" +
                            " не больше уже установленного.\nЗначение capacity не изменено.", false);
                }
            } else {
                commandResult = new CommandResult("Введено неверное название поля.", false);
            }
        } else {
            commandResult = new CommandResult("Модель с id = " + id + " не существует!", false);
        }
        return commandResult;
    }

    @Override
    public String getDescr() {
        return "Заменить значение по ключу, если новое значение больше старого.\n" +
                "Синтаксис: replace_if_greater {id}";
    }
    public static String getName() {
        return name;
    }
}
