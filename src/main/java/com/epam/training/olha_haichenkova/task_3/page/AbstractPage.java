package com.epam.training.olha_haichenkova.task_3.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static java.time.Duration.ofMillis;

public abstract class AbstractPage {

    protected WebDriver driver;
    protected static final int WAIT_TIMEOUT_SECONDS = 10;
    protected static final int POOLING_INTERVAL = 500;
    protected WebDriverWait wait;

    protected AbstractPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS));
    }

    protected WebElement waitToBeClickable(WebElement webElement){
        return wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    protected WebElement waitToBePresent(String xPath) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
    }

    protected WebElement waitToBePresent(By by) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    protected FluentWait<WebDriver> waitWithPooling() {
        return wait.pollingEvery(ofMillis(POOLING_INTERVAL));
    }

    protected abstract AbstractPage openPage();

}
