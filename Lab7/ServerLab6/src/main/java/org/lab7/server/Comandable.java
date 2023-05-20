package org.lab7.server;

public interface Comandable {
    CommandResult execute(Object... o) throws Exception;
    String getDescr();
}
