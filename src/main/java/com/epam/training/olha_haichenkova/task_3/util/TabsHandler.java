package com.epam.training.olha_haichenkova.task_3.util;

import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class TabsHandler {

    private final WebDriver driver;

    public TabsHandler(WebDriver driver){
        this.driver = driver;
    }

    public List<String> listTabs(){
        return new ArrayList<>(driver.getWindowHandles());
    }

    public void switchToTab(int tabNumber){
        driver.switchTo().window(listTabs().get(tabNumber));
    }
}
