package org.lab5;



public interface Comandable {
    public CommandResult execute(Object... o) throws Exception;
    public String getDescr();
}
