package org.lab7.client;


import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Hex;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class Database {
    final static String url = "jdbc:postgresql://localhost:5432/studs";
    final static String username = "s368051";
    final static String password = "zHJsbOAF1VXAcRNF";
    final static String JDBC_DRIVER = "org.postgresql.Driver";
    private static boolean isChanged;

    public static void setIsChanged(boolean isChanged) {
        Database.isChanged = isChanged;
    }

    public static boolean readIsChanged() {
        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from flag_table;")) {

            ObjectMapper objectMapper = new ObjectMapper();
            while (resultSet.next()) {
                isChanged = resultSet.getBoolean("is_changed");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isChanged;
    }

    public static void setBaseIsChanged() {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(url, username, password);
            String sql = "UPDATE flag_table SET is_changed = FALSE WHERE id = 1;";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
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

    public static List<User> readBase() {
        List<String> jsonList = new ArrayList<>();
        List<User> users = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM Users")) {

            ObjectMapper objectMapper = new ObjectMapper();
            while (resultSet.next()) {
                User user = new User();
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
                String json = objectMapper.writeValueAsString(user);
                jsonList.add(json);
            }
            for (String json : jsonList) {
                try {
                    User user = objectMapper.readValue(json, User.class);
                    users.add(user);

                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public static void insertBase(String name, String pass) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(url, username, password);


            String sql = String.format("INSERT INTO Users (name, password) VALUES ('%s', '%s')",
                    name, pass);

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
}


