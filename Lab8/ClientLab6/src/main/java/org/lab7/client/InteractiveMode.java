package org.lab7.client;

import org.lab7.client.arguments.ExtraData;
import org.lab7.client.arguments.ExtraModel;
import org.lab7.client.commands.Exit;
import org.lab7.client.commands.Insert;
import org.lab7.client.commands.Save;
import org.lab7.client.gui.UserGUI;

import javax.swing.*;
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

    static private ArrayList<String> commands;

    public InteractiveMode() {
    }


    public static void interactiveModeOn(ArrayList<String> commands) throws Exception {
        InteractiveMode.commands = commands;
        Scanner sc = new Scanner(System.in);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                UserGUI registrationGUI = null;
                try {
                    registrationGUI = new UserGUI();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                registrationGUI.setVisible(true);
            }
        });

        ConsolePrinter consolePrinter = new ConsolePrinter();
        while (mode) {
            consolePrinter.printToConsole("Введите команду:");
            String input = sc.nextLine();
            consolePrinter.printToConsole("Спасибо! Вы ввели команду " + input);
            String[] arrayOfInput = input.split(" ");
            runCommand(arrayOfInput);

        }
        sc.close();
    }

    public static void InteractiveModeOff() {
        mode = false;
        clientSocket.close();
    }


    public static void runCommand(String[] arrayOfInput) {
        User user = new User(Entry.getName(), Entry.getPass());
        RequestProcessor requestProcessor = new RequestProcessor();
        ConsolePrinter consolePrinter = new ConsolePrinter();
        try {
            Request input;
            CommandManager commandManager = new CommandManager();
            commandManager.makeCollectionOfCommands();
            List<Request> incomingRequests = new ArrayList<>();
            if (arrayOfInput.length > 1) {
                input = new Request(arrayOfInput[0], arrayOfInput[1], user);
            } else {
                input = new Request(arrayOfInput[0], user);
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
