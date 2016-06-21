package model;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class PropertyManager {

    private static final Properties properties = new Properties();

    static {
        try {
            properties.load(PropertyManager.class.getClassLoader().getResourceAsStream("system.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getIntProperty(String propertyName) {
        return getIntProperty(propertyName, 0);
    }

    public static int getIntProperty(String propertyName, int defaultValue) {
        String value = getStringProperty(propertyName, null);
        return (value == null ? defaultValue : Integer.parseInt(value));
    }

    public static boolean getBooleanProperty(String propertyName) {
        return getBooleanProperty(propertyName, false);
    }

    public static boolean getBooleanProperty(String propertyName, boolean defaultValue) {
        Boolean value = Boolean.valueOf(getStringProperty(propertyName));
        return (value == null ? defaultValue : value);
    }

    public static String getStringProperty(String propertyName) {
        return getStringProperty(propertyName, null);
    }

    public static String getStringProperty(String propertyName, String defaultValue) {
        return properties.getProperty(propertyName, defaultValue);
    }
}
