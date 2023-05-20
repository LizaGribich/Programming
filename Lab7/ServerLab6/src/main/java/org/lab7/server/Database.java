package org.lab7.server;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.lab7.server.models.FuelType;
import org.lab7.server.models.Vehicle;
import org.lab7.server.models.VehicleType;

public class Database {
    final static String url = "jdbc:postgresql://localhost:5432/studs";
    final static String username = "s368051";
    final static String password = "zHJsbOAF1VXAcRNF";
    final static String JDBC_DRIVER = "org.postgresql.Driver";

    public static MapWrapper<Integer, Vehicle> readBase() throws Exception {
        List<String> jsonList = new ArrayList<>();
        MapWrapper<Integer, Vehicle> vehicleCollection = new MapWrapper<>(HashMap.class);
        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM Vehicle")) {

            ObjectMapper objectMapper = new ObjectMapper();
            while (resultSet.next()) {
                Vehicle vehicle = new Vehicle();
                vehicle.setID(resultSet.getInt("id"));
                vehicle.setName(resultSet.getString("name"));
                vehicle.setCreationDate(resultSet.getString("creationDate"));
                vehicle.setCapacity(resultSet.getFloat("capacity"));
                vehicle.setEnginePower(Double.valueOf(resultSet.getFloat("enginePower")));
                vehicle.setType(resultSet.getString("type"));
                vehicle.setFuelType(resultSet.getString("fuelType"));
                vehicle.setUser(resultSet.getString("user_name"));
                String json = objectMapper.writeValueAsString(vehicle);
                jsonList.add(json);
            }
            for (String json : jsonList) {
                try {
                    Vehicle vehicle = objectMapper.readValue(json, Vehicle.class);
                    vehicleCollection.put(vehicle.getId(), vehicle);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vehicleCollection;
    }
    public static void remove(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(url, username, password);

            String idS = String.valueOf(id);
            String sql = String.format("DELETE FROM Vehicle WHERE id = %s;",idS);

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
    public static void updateBase(int id, String  name, java.util.Date creationDate, Double enginePower, float capacity, VehicleType type, FuelType fuelType) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(url, username, password);
            String idS = String.valueOf(id);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm a z");
            String dateS = sdf.format(creationDate);
            String enginePowerS = String.valueOf(enginePower);
            String capacityS = String.valueOf(capacity);
            String typeS = type.name();
            String fuelTypeS = fuelType.name();


            String sql = String.format("UPDATE Vehicle " +
                            "SET name =  '%s', " +
                            "creationDate = '%s', " +
                            "enginePower = %s, " +
                            "capacity = %s, " +
                            "type = '%s'," +
                            "fuelType = '%s'" +
                            "WHERE id = %s",
                    name, dateS, enginePowerS, capacityS, typeS, fuelTypeS, idS);

            // Создание объекта PreparedStatement
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // Выполнение запроса
            pstmt.executeUpdate();

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Закрытие ресурсов
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public static void insertBase(String  name, java.util.Date creationDate, Double enginePower, float capacity, VehicleType type, FuelType fuelType, String userName) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Регистрация JDBC драйвера
            Class.forName(JDBC_DRIVER);

            // Открытие соединения
            conn = DriverManager.getConnection(url, username, password);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm a z");
            String dateS = sdf.format(creationDate);
            String enginePowerS = String.valueOf(enginePower);
            String capacityS = String.valueOf(capacity);
            String typeS = type.name();
            String fuelTypeS = fuelType.name();

            // SQL запрос для вставки данных
            String sql = String.format("INSERT INTO Vehicle (id, name, creationDate, enginePower, capacity, type, fuelType, user_name) VALUES (NEXTVAL('my_sequence'), '%s', '%s', %s, %s, '%s', '%s', '%s')",
                    name, dateS, enginePowerS, capacityS, typeS, fuelTypeS, userName);

            // Создание объекта PreparedStatement
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // Выполнение запроса
            pstmt.executeUpdate();

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Закрытие ресурсов
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}

