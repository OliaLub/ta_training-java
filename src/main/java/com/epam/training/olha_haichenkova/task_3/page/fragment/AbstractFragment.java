package com.epam.training.olha_haichenkova.task_3.page.fragment;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class AbstractFragment {

    protected WebDriver driver;
    protected static final int WAIT_TIMEOUT_SECONDS = 10;
    protected WebDriverWait wait;

    protected AbstractFragment(WebDriver driver){
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

}
