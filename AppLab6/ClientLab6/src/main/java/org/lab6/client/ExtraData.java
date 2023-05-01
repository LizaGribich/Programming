package org.lab6.client;

import java.util.Scanner;

public class ExtraData {
    private String name;
    private Number value;
    public void putData(Object... o) {
        ConsolePrinter consolePrinter = new ConsolePrinter();
        Scanner scanner = new Scanner(System.in);
        consolePrinter.printToConsole("Введите название поля, которое хотите заменить (enginePower/capacity):");
        String name = scanner.nextLine();
        Number value = null;
        if (name.equals("enginePower")) {
            consolePrinter.printToConsole("Введите новое значение поля:");
            try {
                value = scanner.nextDouble();
            } catch (Exception e) {
            }
        } else if (name.equals("capacity")) {
            consolePrinter.printToConsole("Введите новое значение поля:");
            try {
                value = scanner.nextFloat();
            } catch (Exception e) {
            }
        }
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Number getValue() {
        return value;
    }
}