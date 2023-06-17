package org.lab7.client;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RequestProcessor {
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final List<Request> requests = new ArrayList<>();
    static DatagramSocket clientSocket;
    static CommandResult commandResult;
    static boolean isChanged;

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
    public static CommandResult getCommandResult() {
        return commandResult;
    }

    public void processRequests(List<Request> incomingRequests) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.submit(() ->
                incomingRequests.parallelStream()
                        .forEach(this::processRequest)
        ).join();
    }


    private void processRequest(Request request){
        ObjectMapper objectMapper = new ObjectMapper();
        ConsolePrinter consolePrinter = new ConsolePrinter();
        Thread thread = new Thread(() -> {

            String inputString;
            try {
                inputString = objectMapper.writeValueAsString(request);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            byte[] sendData = inputString.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 8081);
            try {
                clientSocket.send(sendPacket);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            byte[] receiveData = new byte[1048576];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

            while (true){
                try {
                    clientSocket.receive(receivePacket);
                    String response = new String(receivePacket.getData(), 0, receivePacket.getLength()).trim();
                    CommandResult commandResult = objectMapper.readValue(response, CommandResult.class);
                    this.commandResult = commandResult;
                    this.isChanged = commandResult.getIsChanged();
                    consolePrinter.printToConsole("Ответ от сервера: \n" + commandResult.getMessage());
                    break;
                } catch (SocketTimeoutException e) {
                    consolePrinter.printToConsole("Ошибка подключения.");
                    try {
                        clientSocket = new DatagramSocket();
                        clientSocket.setSoTimeout(5000);
                    } catch (SocketException ex) {
                        throw new RuntimeException(ex);
                    }

                    try {
                        clientSocket.send(sendPacket);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    receivePacket = new DatagramPacket(receiveData, receiveData.length);
                } catch (JsonMappingException e) {
                    throw new RuntimeException(e);
                } catch (JsonParseException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            lock.writeLock().lock();
            try {
                requests.add(request);
            } finally {
                lock.writeLock().unlock();
            }
        });
        thread.start();
    }

    public void sendResponses() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                5, // Количество потоков в пуле
                5, // Максимальное количество потоков в пуле
                1, // Время ожидания до удаления потока из пула (если количество потоков > базового)
                TimeUnit.MINUTES,
                new LinkedBlockingQueue<>() // Очередь задач для выполнения
        );

        lock.readLock().lock();
        try {
            for (Request request : requests) {
                executor.execute(() -> {
                    System.out.println(request.getCommand() + request.getArg());
                });
            }
        } finally {
            lock.readLock().unlock();
        }

        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}


