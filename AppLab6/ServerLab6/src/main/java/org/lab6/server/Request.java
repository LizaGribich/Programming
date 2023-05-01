package org.lab6.server;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Request<T> {
    private String command;
    private T arg;

    public Request(String command) {
        this.command = command;
    }
    @JsonCreator
    public Request(@JsonProperty("command") String command,@JsonProperty("arg") T arg) {
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
