import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.lab5.*;
import org.lab5.models.Vehicle;

import java.io.File;
import java.util.*;

public class AppTest {
    @Test
    public void testDeserialize() throws Exception {
        MapWrapper<Integer, Vehicle> hashMap = new MapWrapper<>(HashMap.class);
        CollectionManager collectionManager = new CollectionManager<>(hashMap);
        MapWrapper<Integer, Vehicle> vehicleCollection = collectionManager.deserialize(new File("C:\\Users\\liza6\\IdeaProjects\\AppLab5_\\src\\data.json"));

        assertNotNull(vehicleCollection);
        assertEquals(5, vehicleCollection.size());
    }

    @Test
    public void testMakeCollectionOfCommands() throws Exception {
        MapWrapper<Integer, Vehicle> hashMap = new MapWrapper<>(HashMap.class);
        CollectionManager collectionManager = new CollectionManager<>(hashMap);
        MapWrapper<Integer, Vehicle> vehicleCollection = collectionManager.deserialize(new File("C:\\Users\\liza6\\IdeaProjects\\AppLab5_\\src\\data.json"));
        CommandManager commandManager = new CommandManager(vehicleCollection);
        commandManager.makeCollectionOfCommands();

        assertNotNull(commandManager.getCommands());
        assertEquals(16, commandManager.getCommands().size());
    }

    @Test
    public void testClear() throws Exception {
        MapWrapper<Integer, Vehicle> hashMap = new MapWrapper<>(HashMap.class);
        CollectionManager collectionManager = new CollectionManager<>(hashMap);
        MapWrapper<Integer, Vehicle> vehicleCollection = collectionManager.deserialize(new File("C:\\Users\\liza6\\IdeaProjects\\AppLab5_\\src\\data.json"));
        CommandManager commandManager = new CommandManager(vehicleCollection);
        commandManager.makeCollectionOfCommands();

        commandManager.getCommands().get("clear").execute();

        assertNotNull(vehicleCollection);
        assertEquals(0, vehicleCollection.size());
    }

    @Test
    public void testInfo() throws Exception {
        MapWrapper<Integer, Vehicle> hashMap = new MapWrapper<>(HashMap.class);
        CollectionManager collectionManager = new CollectionManager<>(hashMap);

        MapWrapper<Integer, Vehicle> vehicleCollection = collectionManager.deserialize(new File("C:\\Users\\liza6\\IdeaProjects\\AppLab5_\\src\\data.json"));

        CommandManager commandManager = new CommandManager(vehicleCollection);
        commandManager.makeCollectionOfCommands();


        CommandResult commandResult = commandManager.getCommands().get("info").execute();

        assertNotNull(vehicleCollection);
        assertEquals("Тип коллеции: " + vehicleCollection.getClass() + "\n"
                + "Колличество элементов: " + vehicleCollection.size()+ "\n", commandResult.getMessage());
    }
    @Test
    public void testExit() throws Exception {
        MapWrapper<Integer, Vehicle> hashMap = new MapWrapper<>(HashMap.class);
        CollectionManager collectionManager = new CollectionManager<>(hashMap);

        MapWrapper<Integer, Vehicle> vehicleCollection = collectionManager.deserialize(new File("C:\\Users\\liza6\\IdeaProjects\\AppLab5_\\src\\data.json"));

        CommandManager commandManager = new CommandManager(vehicleCollection);
        commandManager.makeCollectionOfCommands();


        CommandResult commandResult = commandManager.getCommands().get("exit").execute();

        assertNotNull(vehicleCollection);
        assertEquals("Программа завершена!", commandResult.getMessage());
    }

    @Test
    public void testHistory() throws Exception {
        MapWrapper<Integer, Vehicle> hashMap = new MapWrapper<>(HashMap.class);
        CollectionManager collectionManager = new CollectionManager<>(hashMap);

        MapWrapper<Integer, Vehicle> vehicleCollection = collectionManager.deserialize(new File("C:\\Users\\liza6\\IdeaProjects\\AppLab5_\\src\\data.json"));

        CommandManager commandManager = new CommandManager(vehicleCollection);
        commandManager.makeCollectionOfCommands();

        InteractiveMode.interactiveModeOn(commandManager.getCommands(), vehicleCollection);
        //InteractiveMode.runCommand(commandManager.getCommands(), vehicleCollection, new String[]{"show"});
        //InteractiveMode.runCommand(commandManager.getCommands(), vehicleCollection, new String[]{"clear"});

        CommandResult commandResult = commandManager.getCommands().get("history").execute();

        assertNotNull(vehicleCollection);
        assertEquals(Arrays.toString(new Deque[]{InteractiveMode.getDeque()}), commandResult.getMessage());
    }

    @Test
    public void testSumOfEnginePower() throws Exception {
        MapWrapper<Integer, Vehicle> hashMap = new MapWrapper<>(HashMap.class);
        CollectionManager collectionManager = new CollectionManager<>(hashMap);

        MapWrapper<Integer, Vehicle> vehicleCollection = collectionManager.deserialize(new File("C:\\Users\\liza6\\IdeaProjects\\AppLab5_\\src\\data.json"));

        CommandManager commandManager = new CommandManager(vehicleCollection);
        commandManager.makeCollectionOfCommands();

        Double summa = 0.0;
        for (Vehicle value : vehicleCollection.values()) {
            summa += value.getEnginePower();
        }

        CommandResult commandResult = commandManager.getCommands().get("sum_of_engine_power").execute();

        assertNotNull(vehicleCollection);
        assertEquals("Сумма значений поля EnginePower для всех элементов коллеции = " + summa, commandResult.getMessage());
    }

    @Test
    public void testShow() throws Exception {
        MapWrapper<Integer, Vehicle> hashMap = new MapWrapper<>(HashMap.class);
        CollectionManager collectionManager = new CollectionManager<>(hashMap);

        MapWrapper<Integer, Vehicle> vehicleCollection = collectionManager.deserialize(new File("C:\\Users\\liza6\\IdeaProjects\\AppLab5_\\src\\data.json"));

        CommandManager commandManager = new CommandManager(vehicleCollection);
        commandManager.makeCollectionOfCommands();


        CommandResult commandResult = commandManager.getCommands().get("show").execute();

        assertNotNull(vehicleCollection);
        assertEquals("Коллекция успешно выведена.", commandResult.getMessage());
    }
    @Test
    public void testExecuteScript() throws Exception {
        MapWrapper<Integer, Vehicle> hashMap = new MapWrapper<>(HashMap.class);
        CollectionManager collectionManager = new CollectionManager<>(hashMap);
        MapWrapper<Integer, Vehicle> vehicleCollection = collectionManager.deserialize(new File("C:\\Users\\liza6\\IdeaProjects\\AppLab5_\\src\\data.json"));
        CommandManager commandManager = new CommandManager(vehicleCollection);
        commandManager.makeCollectionOfCommands();

        CommandResult commandResult = commandManager.getCommands().get("execute_script").execute("C:\\Users\\liza6\\IdeaProjects\\AppLab5_\\src\\Script.txt");

        assertNotNull(vehicleCollection);
        assertEquals(true, commandResult.getIsSuccess());
    }

    @Test
    public void testPrintUniqueCapacity() throws Exception {
        MapWrapper<Integer, Vehicle> hashMap = new MapWrapper<>(HashMap.class);
        CollectionManager collectionManager = new CollectionManager<>(hashMap);
        MapWrapper<Integer, Vehicle> vehicleCollection = collectionManager.deserialize(new File("C:\\Users\\liza6\\IdeaProjects\\AppLab5_\\src\\data.json"));
        CommandManager commandManager = new CommandManager(vehicleCollection);
        commandManager.makeCollectionOfCommands();

        CommandResult commandResult = commandManager.getCommands().get("print_unique_capacity").execute();

        HashMap<Integer, Float> CapacityList = new HashMap<>();
        for (int key : vehicleCollection.keySet()) {
            CapacityList.put(key, vehicleCollection.get(key).getCapacity());
        }
        Set<Float> capacityValues = new TreeSet<>(CapacityList.values());

        assertNotNull(vehicleCollection);
        assertEquals(capacityValues.toString(), commandResult.getMessage());
    }


}