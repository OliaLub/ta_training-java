package com.epam.training.olha_haichenkova.task_3.driver;

import com.epam.training.olha_haichenkova.task_3.util.ConfigurationHandler;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverSingleton {
    private static WebDriver driver;

    public static WebDriver getDriver(){
        if (driver == null){
            ConfigurationHandler.setPropertiesFromConfigFile();
            if (System.getProperty("browser").equals("firefox")) {
                driver = new FirefoxDriver();
            } else {
                driver = new ChromeDriver();
            }
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void closeDriver(){
        driver.quit();
    }

}
