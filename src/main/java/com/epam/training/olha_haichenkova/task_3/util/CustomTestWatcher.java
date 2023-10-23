package com.epam.training.olha_haichenkova.task_3.util;

import com.epam.training.olha_haichenkova.task_3.driver.DriverSingleton;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;


public class CustomTestWatcher implements AfterTestExecutionCallback {

    private final Logger logger = LogManager.getRootLogger();

    @Override
    public void afterTestExecution(ExtensionContext extensionContext) {
        if (extensionContext.getExecutionException().isPresent()){
            saveScreenshot();
        }
    }

    private void saveScreenshot(){
        File screenCapture = ((TakesScreenshot) DriverSingleton.getDriver())
                .getScreenshotAs(OutputType.FILE);
        try{
            FileUtils.copyFile(screenCapture, new File(".//target/screenshots/"
                    + StringUtil.getCurrentTimeAsString() + ".png"));
        }catch (IOException e){
            logger.error("Failed to save screenshot: " + e.getLocalizedMessage());
        }
    }

}
