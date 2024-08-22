package com.coherentsolutions.java.basiclasses;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestConfig {

    private static Properties config = new Properties();

    static {
        try (FileInputStream input = new FileInputStream("src/main/resources/conf.properties")) {
            config.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return config.getProperty(key);
    }

    public static String getUsername() {
        return getProperty("login");
    }

    public static String getPassword() {
        return getProperty("password");
    }

    public static String getUrl() {
        return getProperty("url");
    }
}
