package org.lab7.client;

import org.apache.commons.codec.binary.Hex;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Scanner;

public class Entry {
    static private HashMap<String, String> users = new HashMap<>();
    static private String name;
    static private String pass;
    public static String getName() {
        return name;
    }
    public static String getPass() {
        return pass;
    }
    public static void execute() throws Exception {
        for (User user : Database.readBase()) {
            users.put(user.getName(), user.getPassword());
        }
        ConsolePrinter consolePrinter = new ConsolePrinter();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String entryName = sc.nextLine();
            if (entryName.equals("login")) {
                consolePrinter.printToConsole("Введите имя:");
                String nameTemp = sc.nextLine();
                consolePrinter.printToConsole("Введите пароль:");
                String passTemp = sc.nextLine();

                MessageDigest digest = MessageDigest.getInstance("SHA-384");
                byte[] passwordBytes = passTemp.getBytes(StandardCharsets.UTF_8);

                byte[] hashBytes = digest.digest(passwordBytes);

                String hashedPassword = Hex.encodeHexString(hashBytes);
                if (hashedPassword.equals(users.get(nameTemp))) {
                    name = nameTemp;
                    pass = passTemp;
                    consolePrinter.printToConsole("Вы успешно вошли.");
                    break;
                } else {
                    consolePrinter.printToConsole("Неправиль введён логин или пароль.");
                    consolePrinter.printToConsole("Для авторизации введите: register, для регистрации: login.");
                }
            } else if (entryName.equals("register")) {
                consolePrinter.printToConsole("Введите имя:");
                String nameTemp = sc.nextLine();
                consolePrinter.printToConsole("Введите пароль:");
                String passTemp = sc.nextLine();
                if (users.containsKey(nameTemp)) {
                    consolePrinter.printToConsole("Пользователь с таким именем уже существует.");
                } else {

                    name = nameTemp;
                    pass = passTemp;
                    MessageDigest digest = MessageDigest.getInstance("SHA-384");
                    byte[] passwordBytes = passTemp.getBytes(StandardCharsets.UTF_8);

                    byte[] hashBytes = digest.digest(passwordBytes);

                    String hashedPassword = Hex.encodeHexString(hashBytes);
                    users.put(name, hashedPassword);
                    Database.insertBase(name,hashedPassword);
                    consolePrinter.printToConsole("Вы успешно зарегистрировались .");
                    break;
                }
            } else {
                consolePrinter.printToConsole("Неправильно введена команда. Попробуйте еще раз.");
            }
        }
    }
}
