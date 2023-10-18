package com.epam.training.olha_haichenkova.task_3.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationHandler {

    private static final String PATH_TO_CONFIG_FILE = "src//test//resources//config.properties";
    private static final Logger logger = LogManager.getRootLogger();

    public static void setPropertiesFromConfigFile(){
        Properties properties = new Properties();
        try {
            FileInputStream input = new FileInputStream(PATH_TO_CONFIG_FILE);
            properties.load(input);
            input.close();
        } catch (IOException e) {
            logger.error("Failed to read config.properties file: " + e.getLocalizedMessage());
        }
        System.setProperty("browser", properties.getProperty("browser"));
        System.setProperty("environment", properties.getProperty("environment"));
    }

}
