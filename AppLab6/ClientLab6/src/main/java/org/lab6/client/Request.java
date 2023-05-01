package org.lab6.client;


public class Request<T> {
    private String command;
    private T arg;

    public Request(String command) {
        this.command = command;
    }

    public Request(String command, T arg) {
        this.command = command;
        this.arg = arg;
    }

    public Request(String command, T arg, String extraData, Number extraDataSec) {
        this.command = command;
        this.arg = arg;

    }

    public String getCommand() {
        return command;
    }

    public T getArg() {
        return arg;
    }
}
