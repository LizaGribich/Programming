package org.lab7.client;

import org.apache.commons.codec.binary.Hex;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class Register {
    String username;
    String password;
    static private HashMap<String, String> users = new HashMap<>();

    public Register(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean register() throws NoSuchAlgorithmException {
        for (User user : Database.readBase()) {
            users.put(user.getName(), user.getPassword());
        }
        if (users.containsKey(username)) {
            return false;
        } else {
            MessageDigest digest = MessageDigest.getInstance("SHA-384");
            byte[] passwordBytes = password.getBytes(StandardCharsets.UTF_8);

            byte[] hashBytes = digest.digest(passwordBytes);

            String hashedPassword = Hex.encodeHexString(hashBytes);
            users.put(username, hashedPassword);
            Database.insertBase(username, hashedPassword);
            Entry.setName(username);
            Entry.setPass(password);
            return true;
        }
    }
}
