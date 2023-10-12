package com.epam.training.olha_haichenkova.task_3.util;

import com.epam.training.olha_haichenkova.task_3.driver.DriverSingleton;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;


public class CustomTestWatcher implements TestWatcher {

    @Override
    public void testFailed(ExtensionContext context, Throwable cause){
        saveScreenshot();
    }

    private void saveScreenshot(){
        File screenCapture = ((TakesScreenshot) DriverSingleton.getDriver())
                .getScreenshotAs(OutputType.FILE);
        try{
            FileUtils.copyFile(screenCapture, new File(".//target/screenshots/"
                    + StringUtil.getCurrentTimeAsString() + ".png"));
        }catch (IOException e){
            System.out.println("Failed to save screenshot: " + e.getLocalizedMessage());
        }
    }

}
