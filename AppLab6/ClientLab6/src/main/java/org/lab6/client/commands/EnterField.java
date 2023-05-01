package org.lab6.client.commands;

import org.lab6.client.*;


import java.util.Scanner;

public class EnterField {
    Scanner scanner = new Scanner(System.in);
    ConsolePrinter consolePrinter = new ConsolePrinter();

    public String enterString(String name) {
        consolePrinter.printToConsole("Введите " + name + ":");
        return scanner.nextLine();

    }

    public Double enterDouble(String name) {
        Double result;
        while (true) {
            consolePrinter.printToConsole("Введите " + name + ":");
            try {
                String entered_field = scanner.nextLine();
                result = Double.parseDouble(entered_field);
                break;
            } catch (NumberFormatException e) {
                consolePrinter.printToConsole("Неверный тип данных. Требуется Double.");
            }
        }
        return result;
    }

    public float enterFloat(String name) {
        float result;
        while (true) {
            consolePrinter.printToConsole("Введите " + name + ":");
            try {
                String entered_field = scanner.nextLine();
                result = Float.parseFloat(entered_field);
                break;
            } catch (NumberFormatException e) {
                consolePrinter.printToConsole("Неверный тип данных. Требуется float.");
            }
        }
        return result;
    }
}

