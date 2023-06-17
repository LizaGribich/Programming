package org.lab7.client;



public class Entry {
    static private String name;
    static private String pass;

    public static String getName() {
        return name;
    }
    public static String getPass() {
        return pass;
    }


    public static void setName(String name) {
        Entry.name = name;
    }

    public static void setPass(String pass) {
        Entry.pass = pass;
    }
}
