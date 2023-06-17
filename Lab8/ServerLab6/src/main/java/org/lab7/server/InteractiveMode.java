package org.lab7.server;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.lab7.server.arguments.ExtraData;
import org.lab7.server.arguments.ExtraModel;
import org.lab7.server.arguments.IntArgument;
import org.lab7.server.arguments.StringArgument;
import org.lab7.server.models.Vehicle;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.*;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class InteractiveMode {
    static boolean mode = true;
    static Deque<String> deque = new ArrayDeque<>();
    static Multimap<String, String> history = ArrayListMultimap.create();

    static DatagramSocket serverSocket;

    static {
        try {
            serverSocket = new DatagramSocket(8081);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }

    static byte[] receiveData = new byte[1048576];

    public InteractiveMode() {
    }

    public static void interactiveModeOn(HashMap<String, Comandable> commands, MapWrapper<Integer, Vehicle> models) throws Exception {
        while (mode) {
            runCommand(commands, models);
        }

    }

    static InetAddress IPAddress;
    static int port;
    static ArrayList<String> resScript = new ArrayList<>();

    public static void InteractiveModeOff() {
    }

    public static Multimap<String, String> getDeque() {
        return history;
    }

    public synchronized static void runCommand(HashMap<String, Comandable> commands, MapWrapper<Integer, Vehicle> models) throws Exception {

        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        serverSocket.receive(receivePacket);

        IPAddress = receivePacket.getAddress();
        port = receivePacket.getPort();

        String sentence = new String(receivePacket.getData());

        ObjectMapper objectMapper = new ObjectMapper();
        JavaType javaType = TypeFactory.defaultInstance().constructParametricType(Request.class, Object.class);
        Request request = objectMapper.readValue(sentence, javaType);
        CommandResult res;
        RequestProcessor requestProcessor = new RequestProcessor(serverSocket);
        if (!(commands.get(request.getCommand()) instanceof ExtraData || commands.get(request.getCommand()) instanceof ExtraModel)) {
            if ((request.getArg() == null) && ((commands.get(request.getCommand()) instanceof IntArgument) || (commands.get(request.getCommand()) instanceof StringArgument))) {
                res = new CommandResult("Неправильно введена команда: нет аргумента.", false);
                requestProcessor.processRequest(res, IPAddress, port);
            } else if (request.getArg() != null) {
                if (commands.get(request.getCommand()) instanceof IntArgument) {
                    try {
                        Integer.parseInt(request.getArg().toString());

                        requestProcessor.processRequest(commands.get(request.getCommand()), request, IPAddress, port);
                    } catch (NumberFormatException e) {
                        res = new CommandResult("Неправильно введена команда: неверный тип данных аргумента.", false);
                        requestProcessor.processRequest(res, IPAddress, port);
                    }
                } else {
                    requestProcessor.processRequest(commands.get(request.getCommand()), request, IPAddress, port);
                }
            } else {
                requestProcessor.processRequest(commands.get(request.getCommand()), request, IPAddress, port);
            }
        } else {
            requestProcessor.processRequest(commands.get(request.getCommand()), request, IPAddress, port);
        }

        if (history.get(request.getUser().getName()).size() > 11) {
            Iterator<String> iteratorM = history.keySet().iterator();
            while (iteratorM.hasNext()) {
                String key = iteratorM.next();
                List<String> values = new ArrayList<>(history.get(key));
                if (!values.isEmpty()) {
                    Iterator<String> iterator = values.iterator();
                    iterator.next();
                    iterator.remove();
                    history.replaceValues(key, values);
                }
            }
            history.put(request.getUser().getName(), request.getCommand());
        } else {
            history.put(request.getUser().getName(), request.getCommand());
        }


    }

    public static void runScript(HashMap<String, Comandable> commands, MapWrapper<Integer, Vehicle> models, String[] arrayOfInput) throws IOException {
        CommandResult res;
        try {
            if (arrayOfInput.length > 1) {
                try {
                    int id = Integer.parseInt(arrayOfInput[1]);
                    res = (commands.get(arrayOfInput[0]).execute(id));
                } catch (NumberFormatException e) {
                    res = commands.get(arrayOfInput[0]).execute(arrayOfInput[1]);
                }
            } else {
                res = commands.get(arrayOfInput[0]).execute(models);
            }
            resScript.add(res.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static String returnScriptRes() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String string = "";
        for (String element : resScript) {
            string += objectMapper.writeValueAsString(element) + "\n";
        }
        string = string.substring(0, string.length() - 1);
        resScript.clear();
        return string;
    }

}
