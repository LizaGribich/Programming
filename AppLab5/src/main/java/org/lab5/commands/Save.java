package org.lab5.commands;

import org.lab5.models.Vehicle;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.lab5.Comandable;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;

public class Save implements Comandable {
    static String name = "save";


    public static String getName() {
        return name;
    }

    @Override
    public String getDescr() {
        return "Сохранить коллекцию в файл.\n" +
                "Синтаксис: save";
    }

    @Override
    public void execute(HashMap<Integer, Vehicle> hashMap, Object... o) throws ParseException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        writer.writeValue(new File("C:\\Users\\liza6\\IdeaProjects\\AppLab5\\src\\output.json"), hashMap);
        System.out.println("Коллекция успешно сохранена!");
    }
}
