package org.lab6.client.commands;


import org.lab6.client.*;
import org.lab6.client.models.Vehicle;


public class Update implements Comandable {
    static String name = "update";
    private MapWrapper<Integer, Vehicle> hashMap;

    public Update(MapWrapper<Integer, Vehicle> hashMap) {
        this.hashMap = hashMap;
    }


    public static String getName() {
        return name;
    }

    @Override
    public String getDescr() {
        return "Обновить значение элемента коллекции, id которого равен заданному.\n" +
                "Синтаксис: update {id}";
    }

    @Override
    public CommandResult execute(Object... o) {
        int id = Integer.parseInt(o[0].toString());
        CommandResult commandResult;
        if (hashMap.get(id) != null) {
            CreationModel creationModel = new CreationModel();
            hashMap.put(id, creationModel.createModel(id));

            commandResult = new CommandResult("Модель с id = " + id +
                    " успешно заменена!", true);
        } else {
            commandResult = new CommandResult("Модель с id = " + id +
                    " не существует!", false);
        }
        return commandResult;
    }
}
