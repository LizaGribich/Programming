package org.lab5;

import org.lab5.models.Vehicle;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;

public interface Comandable {
    public void execute(HashMap<Integer, Vehicle> hashMap, Object... o) throws ParseException, IOException;
    public String getDescr();
}
