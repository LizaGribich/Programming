package org.lab6.server;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.lab6.server.models.Vehicle;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.*;

public class InteractiveMode {
    static boolean mode = true;
    static Deque<String> deque = new ArrayDeque<>();


    static DatagramSocket serverSocket;

    static {
        try {
            serverSocket = new DatagramSocket(8081);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }

    static byte[] receiveData = new byte[1048576];
    static byte[] sendData;

    public InteractiveMode() throws SocketException {
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

    public static Deque<String> getDeque() {
        return deque;
    }

    public static void runCommand(HashMap<String, Comandable> commands, MapWrapper<Integer, Vehicle> models) throws Exception {
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        serverSocket.receive(receivePacket);

        IPAddress = receivePacket.getAddress();
        port = receivePacket.getPort();

        String sentence = new String(receivePacket.getData());

        ObjectMapper objectMapper = new ObjectMapper();
        JavaType javaType = TypeFactory.defaultInstance().constructParametricType(Request.class, Object.class);
        Request request = objectMapper.readValue(sentence, javaType);
        CommandResult res;
        if (request.getArg() != null) {
            res = commands.get(request.getCommand()).execute(request.getArg());
        } else {
            res = commands.get(request.getCommand()).execute(models);
        }

        String string = objectMapper.writeValueAsString(res);
        sendData = string.getBytes();


        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
        serverSocket.send(sendPacket);

        if (deque.size() > 12) {
            deque.pollFirst();
            deque.add(request.getCommand());
        } else {
            deque.add(request.getCommand());
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
            System.out.println(e.toString());
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
