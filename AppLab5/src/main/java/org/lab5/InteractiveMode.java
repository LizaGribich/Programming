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

    public static void interactiveModeOn(HashMap<String, Comandable> commands, HashMap<Integer, Vehicle> models) throws IOException {
        Scanner sc = new Scanner(System.in);
        while (mode) {
            System.out.println("Введите команду:");
            String input = sc.nextLine();
            System.out.println("Спасибо! Вы ввели команду " + input);
            String[] ArrayOfInput = input.split(" ");
            runCommand(commands, models, ArrayOfInput);
        }
        sc.close();
        System.out.println("Программа завершена!");
    }

    public static void InteractiveModeOff() {
        mode = false;
    }

    public static Deque<String> getDeque() {
        return deque;
    }

    public static void runCommand(HashMap<String, Comandable> commands, HashMap<Integer, Vehicle> models, String[] arrayOfInput) throws IOException{
        try {
            if (arrayOfInput.length > 1) {
                try {
                    int id = Integer.parseInt(arrayOfInput[1]);
                    commands.get(arrayOfInput[0]).execute(id);
                } catch (NumberFormatException e) {
                    commands.get(arrayOfInput[0]).execute(arrayOfInput[1]);
                }
            } else {
                commands.get(arrayOfInput[0]).execute(models);
            }
            if (deque.size() > 12) {
                deque.pollFirst();
                deque.add(arrayOfInput[0]);
            } else {
                deque.add(arrayOfInput[0]);
            }
        } catch (NullPointerException e) {
            System.out.println(arrayOfInput[0]);
            System.out.println("Неправильно введена команда.\n" +
                    "Для справки по доступным командам введите help.");
        }
    }
}
