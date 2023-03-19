package org.lab5.commands;


import org.lab5.CreationModel;
import org.lab5.Comandable;
import org.lab5.models.Vehicle;


import java.text.ParseException;
import java.util.HashMap;


public class Update implements Comandable {
    static String name = "update";
    private HashMap<Integer, Vehicle> hashMap = new HashMap<>();
    public Update (HashMap<Integer, Vehicle> hashMap) {
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
    public void execute(Object... o) {
        int id = Integer.parseInt(o[0].toString());
        if (hashMap.get(id) != null){
            CreationModel creationModel = new CreationModel();
            hashMap.put(id, creationModel.createModel(hashMap, id));

            System.out.println("Модель с id = " + id + " успешно заменена!");
        } else {
            System.out.println("Модель с id = " + id + " не существует!");
        }

    }
}
