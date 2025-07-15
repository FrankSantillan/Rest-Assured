package utils;

import java.util.Properties;
import java.io.InputStream;

public class ConfigReader {
    private static final Properties props = new Properties();

    static {
        try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input != null) {
                props.load(input);
            } else {
                throw new RuntimeException("config.properties not found!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String get(String key) {
        String value = props.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Missing property for key: " + key);
        }
        System.out.println("ConfigReader: Loaded " + key + " = " + (key.equals("GITHUB_TOKEN") ? "[PROTECTED]" : value));
        return value;
    }

}