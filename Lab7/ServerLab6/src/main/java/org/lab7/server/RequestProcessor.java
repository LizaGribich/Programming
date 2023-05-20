package org.lab7.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RequestProcessor {
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final List<Request> requests = new ArrayList<>();
    private DatagramSocket serverSocket;

    public RequestProcessor(DatagramSocket serverSocket) {
        this.serverSocket = serverSocket;

    }

    public void processRequest(CommandResult commandResult, InetAddress ip, int port) {
        sendResponses(commandResult, ip, port);
    }
    public void processRequest(Comandable c, Request request, InetAddress ip, int port) {
        Thread thread = new Thread(() -> {
            try {
                CommandResult res = c.execute(request.getArg(), request.getUser().getName());
                sendResponses(res, ip, port);
            } catch (Exception e) {
                throw new RuntimeException(e);
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

    public void sendResponses(CommandResult res, InetAddress ip, int port) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                5, // Количество потоков в пуле
                5, // Максимальное количество потоков в пуле
                1, // Время ожидания до удаления потока из пула (если количество потоков > базового)
                TimeUnit.MINUTES,
                new LinkedBlockingQueue<>() // Очередь задач для выполнения
        );

        lock.readLock().lock();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
                executor.execute(() -> {
                    byte[] sendData;
                    String string;
                    try {
                        string = objectMapper.writeValueAsString(res);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                    sendData = string.getBytes();

                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ip, port);
                    try {
                        serverSocket.send(sendPacket);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

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

