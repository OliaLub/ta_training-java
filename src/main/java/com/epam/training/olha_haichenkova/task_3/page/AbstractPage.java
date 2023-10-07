package com.epam.training.olha_haichenkova.task_3.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class AbstractPage {

    protected WebDriver driver;
    protected static final int WAIT_TIMEOUT_SECONDS = 5;
    protected WebDriverWait wait;

    protected AbstractPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS));
    }

    protected WebElement waitToBeClickable(String xPath){
        return wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(xPath))));
    }

    protected WebElement waitToBeClickable(WebElement webElement){
        return wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    protected WebElement waitToBePresent(String xPath) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
    }

    protected static String isolateNumberFromString(String stringWithNumber) {
        String[] splitMessage = stringWithNumber.split("\\s");
        String number = null;
        for (String element : splitMessage) {
            if (element.matches("[0-9,.]+")) {
                number = element;
                break;
            }
        }
        if (number == null) {
            throw new IllegalArgumentException("There is no number in string");
        }
        return number;
    }

    protected abstract AbstractPage openPage();

}
