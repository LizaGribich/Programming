package org.lab6.client;



public interface Comandable {
    public CommandResult execute(Object... o) throws Exception;
    public String getDescr();
}
