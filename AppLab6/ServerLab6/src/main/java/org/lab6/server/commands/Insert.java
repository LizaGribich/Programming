package org.lab6.server.commands;

import org.lab6.server.models.FuelType;
import org.lab6.server.models.VehicleType;
import org.lab6.server.*;
import org.lab6.server.models.Coordinates;
import org.lab6.server.models.Vehicle;

import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class Insert implements Comandable, ExtraModel {
    static String name = "insert";
    private MapWrapper<Integer, Vehicle> hashMap;
    public Insert (MapWrapper<Integer, Vehicle> hashMap) {
        this.hashMap = hashMap;
    }

    @Override
    public CommandResult execute(Object... o) {

        RandomNum randomNum = new RandomNum();
        int id = randomNum.createRandomNum(hashMap);

        String inputString = Arrays.toString(o);
        String[] inputValues = inputString.substring(1, inputString.length() - 1).split(" ");

        String name = inputValues[0];
        Double x = Double.parseDouble(inputValues[1]);
        float y = Float.parseFloat(inputValues[2]);
        Double enginePower = Double.parseDouble(inputValues[3]);
        float capacity = Float.parseFloat(inputValues[4]);
        String type = inputValues[5];
        String fuelType = inputValues[6];

        Date creationDate = new Date(ThreadLocalRandom.current().nextInt() * 1000L);


        hashMap.put(id, new Vehicle(id, name, new Coordinates(x, y), creationDate, enginePower, capacity,
                VehicleType.valueOf(type), FuelType.valueOf(fuelType)));

        return new CommandResult("Модель с id = " + id + " успешно добавлена!", true);
    }



    @Override
    public String getDescr() {
        return "Добавить новый элемент с автоматически сгенерированным ключом.\n" +
                "Синтаксис: insert";
    }

    public static String getName() {
        return name;
    }
}
