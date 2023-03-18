package org.lab5.commands;

import org.lab5.Comandable;
import org.lab5.models.Vehicle;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class ReplaceIfGreater implements Comandable {
    static String name = "replace_if_greater";

    @Override
    public void execute(HashMap<Integer, Vehicle> hashMap, Object... o) throws ParseException, IOException {
        int id = Integer.valueOf(Arrays.toString(o).replaceAll("]", "").substring(1));
        if (hashMap.get(id) != null) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите название поля, которое хотите заменить (enginePower/capacity):");
            String name = scanner.nextLine();
            if (name.equals("enginePower")) {
                System.out.println("Введите новое значение поля:");
                Double enginePower = scanner.nextDouble();
                if (hashMap.get(id).getEnginePower() - enginePower < 0) {
                    hashMap.get(id).setEnginePower(enginePower);
                    System.out.println("Значение enginePower успешно изменено!");
                } else {
                    System.out.println("Введённое значение не больше уже установленного.\nЗначение enginePower не изменено.");
                }
            } else if (name.equals("capacity")) {
                System.out.println("Введите новое значение поля:");
                float capacity = scanner.nextFloat();
                if (hashMap.get(id).getCapacity() - capacity < 0) {
                    hashMap.get(id).setCapacity(capacity);
                    System.out.println("Значение capacity успешно изменено!");
                } else {
                    System.out.println("Введённое значение не больше уже установленного.\nЗначение capacity не изменено.");
                }
            } else {
                System.out.println("Введено неверное название поля.");
            }
        } else {
            System.out.println("Модель с id = " + id + " не существует!");
        }
    }

    @Override
    public String getDescr() {
        return "Заменить значение по ключу, если новое значение больше старого.\n" +
                "Синтаксис: replace_if_greater";
    }
    public static String getName() {
        return name;
    }
}
