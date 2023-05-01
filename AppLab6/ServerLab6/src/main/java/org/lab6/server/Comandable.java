package org.lab6.server;

public interface Comandable {
    public CommandResult execute(Object... o) throws Exception;
    public String getDescr();
}
