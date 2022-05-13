package util;

import lombok.experimental.UtilityClass;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

@UtilityClass
public final class PropertiesManager {

    private static final Properties PROPERTIES = new Properties();




    static {
        setProperties();
    }

    public static String getProperties(String key) {
        return PROPERTIES.getProperty(key);
    }

    private static void setProperties() {
        try {
            PROPERTIES.load(Files.newInputStream(new File("C:\\Users\\Артём\\IdeaProjects\\JDBC\\resourses\\application.properties").toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
