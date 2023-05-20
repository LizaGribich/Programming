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

    public static List<User> readBase(){
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

    public static void insertBase(String  name, String pass) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(url, username, password);
            MessageDigest digest = MessageDigest.getInstance("SHA-384");

            byte[] passwordBytes = pass.getBytes(StandardCharsets.UTF_8);

            byte[] hashBytes = digest.digest(passwordBytes);

            String hashedPassword = Hex.encodeHexString(hashBytes);


            String sql = String.format("INSERT INTO Users (name, password) VALUES ('%s', '%s')",
                    name, hashedPassword);

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


