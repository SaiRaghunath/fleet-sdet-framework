package com.fleet.framework.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigReader {
    private static final String CONFIG_FILE = "config.properties";
    private static final Properties PROPERTIES = loadProperties();

    private ConfigReader() {
    }

    private static Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (inputStream == null) {
                throw new IllegalStateException("Unable to find " + CONFIG_FILE + " in classpath.");
            }
            properties.load(inputStream);
            return properties;
        } catch (IOException exception) {
            throw new IllegalStateException("Failed to load configuration file: " + CONFIG_FILE, exception);
        }
    }

    public static String get(String key) {
        String value = PROPERTIES.getProperty(key);
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("Missing configuration key: " + key);
        }
        return value.trim();
    }
}
