package org.lab7.server;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Request<T> {
    private String command;
    private T arg;
    private User user;
    public Request(String command) {
        this.command = command;
    }

    @JsonCreator
    public Request(@JsonProperty("command") String command,@JsonProperty("arg") T arg, @JsonProperty("user") User user) {
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
