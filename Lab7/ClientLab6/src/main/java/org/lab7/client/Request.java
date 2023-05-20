package org.lab7.client;


public class Request<T> {
    private String command;
    private T arg;
    private User user;

    public Request(String command, User user) {
        this.command = command;
        this.user = user;
    }

    public Request(String command, T arg, User user) {
        this.command = command;
        this.arg = arg;
        this.user = user;
    }


    public User getUser() {
        return user;
    }

    public String getCommand() {
        return command;
    }

    public T getArg() {
        return arg;
    }
}
