package org.lab5;

import org.lab5.models.Coordinates;
import org.lab5.models.FuelType;
import org.lab5.models.Vehicle;
import org.lab5.models.VehicleType;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class CreationModel {
    public static void createModel(HashMap<Integer, Vehicle> hashMap, Integer id) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите name:");
        String name = scanner.nextLine();
        System.out.println("Введите coordinates x:");
        Double x = scanner.nextDouble();
        System.out.println("Введите coordinates y:");
        float y = scanner.nextFloat();
        Date creationDate = new Date(ThreadLocalRandom.current().nextInt() * 1000L);
        System.out.println("Поле creationDate генерируется автоматически:" + creationDate);
        System.out.println("Введите enginePower:");
        Double enginePower = scanner.nextDouble();
        System.out.println("Введите capacity:");
        float capacity = scanner.nextFloat();
        System.out.println("Введите type (CAR, DRONE, CHOPPER, HOVERBOARD, SPACESHIP):");
        String typeString = scanner.next();
        System.out.println("Введите fuelType (GASOLINE, MANPOWER, PLASMA, ANTIMATTER):");
        String fuelTypeString = scanner.next();

        hashMap.put(id, new Vehicle(id, name, new Coordinates(x, y), creationDate, enginePower, capacity,
                VehicleType.valueOf(typeString), FuelType.valueOf(fuelTypeString)));

    }
}
