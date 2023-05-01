package org.lab6.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.lab6.client.commands.Exit;

import java.io.IOException;
import java.net.*;
import java.util.*;

public class InteractiveMode {
    static boolean mode = true;
    static Deque<String> deque = new ArrayDeque<>();
    static DatagramSocket clientSocket;

    static {
        try {
            clientSocket = new DatagramSocket();
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }

    static InetAddress IPAddress;

    static {
        try {
            IPAddress = InetAddress.getByName("localhost");
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    public InteractiveMode() throws SocketException, UnknownHostException {
    }


    public static void interactiveModeOn(ArrayList<String> commands) throws IOException {
        Scanner sc = new Scanner(System.in);
        ConsolePrinter consolePrinter = new ConsolePrinter();


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

    public static Deque<String> getDeque() {
        return deque;
    }

    public static void runCommand(ArrayList<String> commands, String[] arrayOfInput) throws IOException {
        ConsolePrinter consolePrinter = new ConsolePrinter();
        try {
            Request input;
            if (arrayOfInput[0].equals(Exit.getName())) {
                InteractiveModeOff();
            }
            CommandManager commandManager = new CommandManager();
            commandManager.makeCollectionOfCommands();
            if (commandManager.getCommandsIntArgument().contains(arrayOfInput[0])) {
                try {
                    int id = Integer.parseInt(arrayOfInput[1]);
                } catch (NumberFormatException e) {
                    throw new Exception();
                }
            }
            if (commandManager.getCommandsStringArgument().contains(arrayOfInput[0])) {
                try {
                    String temp = arrayOfInput[1];
                } catch (NumberFormatException e) {
                    throw new Exception();
                }
            }

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
                    input = new Request<Integer>(arrayOfInput[0], id);

                } catch (NumberFormatException e) {
                    input = new Request<String>(arrayOfInput[0], arrayOfInput[1]);
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
                    input = new Request(arrayOfInput[0], extraInput[0]);
                } else {
                    input = new Request(arrayOfInput[0]);
                }
            }

            if (commands.contains(input.getCommand())) {

                ObjectMapper objectMapper = new ObjectMapper();
                String inputString = objectMapper.writeValueAsString(input);
                byte[] sendData = inputString.getBytes();

                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 8081);
                clientSocket.send(sendPacket);

                byte[] receiveData = new byte[1048576];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                clientSocket.receive(receivePacket);

                String response = new String(receivePacket.getData(), 0, receivePacket.getLength()).trim();
                CommandResult commandResult = objectMapper.readValue(response, CommandResult.class);
                consolePrinter.printToConsole("Ответ от сервера: \n" + commandResult.getMessage());

            } else {
                consolePrinter.printToConsole("Неправильно введена команда.\n" +
                        "Для справки по доступным командам введите help.");
            }
        } catch (SocketException e) {
            consolePrinter.printToConsole("Сокет закрыт.");
        } catch (Exception e) {
            consolePrinter.printToConsole(arrayOfInput[0]);
            consolePrinter.printToConsole("Неправильно введена команда.\n" +
                    "Для справки по доступным командам введите help.");
            consolePrinter.printToConsole(e.getMessage());
        }
    }
}
