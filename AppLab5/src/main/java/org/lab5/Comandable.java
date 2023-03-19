package org.lab5;


import java.io.IOException;

public interface Comandable {
    public void execute(Object... o) throws IOException;
    public String getDescr();
}
