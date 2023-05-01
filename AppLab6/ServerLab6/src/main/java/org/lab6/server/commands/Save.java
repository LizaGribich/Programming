package org.lab6.server.commands;

import org.lab6.server.*;
import org.lab6.server.models.Vehicle;

import java.io.File;
import java.io.IOException;

public class Save implements Comandable {
    static String name = "save";
    private MapWrapper<Integer, Vehicle> hashMap;
    public Save (MapWrapper<Integer, Vehicle> hashMap) {
        this.hashMap = hashMap;
    }

    public static String getName() {
        return name;
    }

    @Override
    public String getDescr() {
        return "Сохранить коллекцию в файл.\n" +
                "Синтаксис: save";
    }

    @Override
    public CommandResult execute(Object... o) throws IOException {
        CollectionManager collectionManager = new CollectionManager<>(hashMap);
        collectionManager.serialize(new File("C:\\Users\\liza6\\IdeaProjects\\AppLab5_\\src\\output.json"));
        return new CommandResult("Коллекция успешно сохранена!", true);
    }


}
