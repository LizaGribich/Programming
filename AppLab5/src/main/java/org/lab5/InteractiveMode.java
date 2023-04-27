package org.lab5;

import org.lab5.models.Vehicle;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Scanner;

public class InteractiveMode {
    static boolean mode = true;
    static Deque<String> deque = new ArrayDeque<>();

    public static void interactiveModeOn(HashMap<String, Comandable> commands, MapWrapper<Integer, Vehicle> models) throws IOException {
        Scanner sc = new Scanner(System.in);
        ConsolePrinter consolePrinter = new ConsolePrinter();
        while (mode) {
            consolePrinter.printToConsole("Введите команду:");
            String input = sc.nextLine();
            consolePrinter.printToConsole("Спасибо! Вы ввели команду " + input);
            String[] ArrayOfInput = input.split(" ");
            runCommand(commands, models, ArrayOfInput);
        }
        sc.close();
    }

    public static void InteractiveModeOff() {
        mode = false;
    }

    public static Deque<String> getDeque() {
        return deque;
    }

    public static void runCommand(HashMap<String, Comandable> commands, MapWrapper<Integer, Vehicle> models, String[] arrayOfInput) throws IOException{
        ConsolePrinter consolePrinter = new ConsolePrinter();
        try {
            if (arrayOfInput.length > 1) {
                try {
                    int id = Integer.parseInt(arrayOfInput[1]);
                    consolePrinter.printResToConsole(commands.get(arrayOfInput[0]).execute(id));
                } catch (NumberFormatException e) {
                    consolePrinter.printResToConsole(commands.get(arrayOfInput[0]).execute(arrayOfInput[1]));
                }
            } else {
                consolePrinter.printResToConsole(commands.get(arrayOfInput[0]).execute(models));
            }
            if (deque.size() > 12) {
                deque.pollFirst();
                deque.add(arrayOfInput[0]);
            } else {
                deque.add(arrayOfInput[0]);
            }
        } catch (Exception e) {
            consolePrinter.printToConsole(arrayOfInput[0]);
            consolePrinter.printToConsole("Неправильно введена команда.\n" +
                    "Для справки по доступным командам введите help.");
            consolePrinter.printToConsole(e.getMessage());
        }
    }
}
