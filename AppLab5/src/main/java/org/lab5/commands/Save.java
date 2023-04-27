package org.lab5.commands;

import org.lab5.CollectionManager;
import org.lab5.CommandResult;
import org.lab5.MapWrapper;
import org.lab5.models.Vehicle;
import org.lab5.Comandable;

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
