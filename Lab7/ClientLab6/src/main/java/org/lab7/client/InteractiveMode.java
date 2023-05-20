package org.lab7.client;

import org.lab7.client.arguments.ExtraData;
import org.lab7.client.arguments.ExtraModel;
import org.lab7.client.commands.Exit;
import org.lab7.client.commands.Save;

import java.net.*;
import java.util.*;

public class InteractiveMode {
    static boolean mode = true;
    static DatagramSocket clientSocket;

    static {
        try {
            clientSocket = new DatagramSocket();
            clientSocket.setSoTimeout(5000);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }

    static InetAddress IPAddress;

    static {
        try {
            IPAddress = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    public InteractiveMode() {
    }


    public static void interactiveModeOn(ArrayList<String> commands) throws Exception {
        Scanner sc = new Scanner(System.in);
        ConsolePrinter consolePrinter = new ConsolePrinter();
        consolePrinter.printToConsole("Добрый день! Пожалуйста, авторизуйтесь/зарегестрируйтесь.");
        consolePrinter.printToConsole("Для авторизации введите: register, для регистрации: login.");
        Entry.execute();


        while (mode) {
            consolePrinter.printToConsole("Введите команду:");
            String input = sc.nextLine();
            consolePrinter.printToConsole("Спасибо! Вы ввели команду " + input);
            String[] ArrayOfInput = input.split(" ");
            runCommand(commands, ArrayOfInput);
        }
        sc.close();
    }

    public static void InteractiveModeOff() {
        mode = false;
        clientSocket.close();
    }


    public static void runCommand(ArrayList<String> commands, String[] arrayOfInput) {
        User user = new User(Entry.getName(), Entry.getPass());
        RequestProcessor requestProcessor = new RequestProcessor();
        ConsolePrinter consolePrinter = new ConsolePrinter();
        try {
            Request input;
            CommandManager commandManager = new CommandManager();
            commandManager.makeCollectionOfCommands();
            List<Request> incomingRequests = new ArrayList<>();
            if (arrayOfInput.length > 1) {
                if (commandManager.getCommandsExtraData().contains(arrayOfInput[0])) {
                    ExtraData extraData = new ExtraData();
                    extraData.putData();
                    arrayOfInput[1] += " " + extraData.getName() + " " + extraData.getValue();
                } else if (commandManager.getCommandsExtraModel().contains(arrayOfInput[0])) {
                    ExtraModel extraModel = new ExtraModel();
                    extraModel.putData();
                    arrayOfInput[1] += " " + extraModel.getName() + " " + extraModel.getX()
                            + " " + extraModel.getY() + " " + extraModel.getEnginePower()
                            + " " + extraModel.getCapacity() + " " + extraModel.getType()
                            + " " + extraModel.getFuelType();
                }
                try {
                    int id = Integer.parseInt(arrayOfInput[1]);
                    input = new Request<>(arrayOfInput[0], id, user);

                } catch (NumberFormatException e) {
                    input = new Request<>(arrayOfInput[0], arrayOfInput[1], user);
                }
            } else {
                if (commandManager.getCommandsExtraModel().contains(arrayOfInput[0])) {
                    ExtraModel extraModel = new ExtraModel();
                    extraModel.putData();
                    String[] extraInput = new String[10];
                    extraInput[0] = extraModel.getName() + " " + extraModel.getX()
                            + " " + extraModel.getY() + " " + extraModel.getEnginePower()
                            + " " + extraModel.getCapacity() + " " + extraModel.getType()
                            + " " + extraModel.getFuelType();
                    input = new Request(arrayOfInput[0], extraInput[0], user);
                } else {
                    input = new Request(arrayOfInput[0], user);
                }
            }
            boolean flag = false;
            if (commands.contains(input.getCommand())) {
                if (arrayOfInput[0].equals(Exit.getName())) {
                    input = new Request(Save.getName(), user);
                    flag = true;
                }

                incomingRequests.add(input);
                requestProcessor.processRequests(incomingRequests);
                try {
                    Thread.sleep(1000); // Добавлено для демонстрации
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (flag) {
                    InteractiveMode.InteractiveModeOff();
                }



            } else {
                consolePrinter.printToConsole("Неправильно введена команда.\n" +
                        "Для справки по доступным командам введите help.");
            }
        } catch (Exception e) {
            consolePrinter.printToConsole(arrayOfInput[0]);
            consolePrinter.printToConsole("Неправильно введена команда.\n" +
                    "Для справки по доступным командам введите help.");
            consolePrinter.printToConsole(e.getMessage());
        }
    }
}
