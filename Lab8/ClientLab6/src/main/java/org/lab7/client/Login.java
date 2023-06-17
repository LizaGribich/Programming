package org.lab7.client;

import org.apache.commons.codec.binary.Hex;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class Login {
    String username;
    String password;
    static private HashMap<String, String> users = new HashMap<>();
    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public boolean login() throws NoSuchAlgorithmException {
        for (User user : Database.readBase()) {
            users.put(user.getName(), user.getPassword());
        }
        MessageDigest digest = MessageDigest.getInstance("SHA-384");
        byte[] passwordBytes = password.getBytes(StandardCharsets.UTF_8);

        byte[] hashBytes = digest.digest(passwordBytes);

        String hashedPassword = Hex.encodeHexString(hashBytes);
        if (hashedPassword.equals(users.get(username))) {
            Entry.setName(username);
            Entry.setPass(password);
            return true;
        }
        return false;
    }
}
