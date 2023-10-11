package com.epam.training.olha_haichenkova.task_3.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class YopmailClientPage extends AbstractPage{

    private static final String LETTER_IFRAME_ID = "ifmail";
    private static final String IFRAME_BODY_TAG_NAME = "body";
    private static final String IFRAME_BODY_ATTRIBUTE = "class";
    private static final String IFRAME_BODY_CLASS = "bodymail yscrollbar";

    @FindBy(xpath = "//tbody//td/h2")
    private WebElement messageBody;

    @FindBy(tagName = "body")
    private WebElement iframeBody;

    @FindBy(xpath = "//button[@id='refresh']")
    private WebElement refreshMailboxButton;

    public YopmailClientPage(WebDriver driver) {
        super(driver);
    }

    public String readReceivedEmail() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(LETTER_IFRAME_ID)));
        driver.switchTo().frame(LETTER_IFRAME_ID);
        String bodyClass = iframeBody.getAttribute(IFRAME_BODY_ATTRIBUTE);
        while (!bodyClass.equals(IFRAME_BODY_CLASS)){
            waitForLetter();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName(IFRAME_BODY_TAG_NAME)));
            bodyClass = iframeBody.getAttribute(IFRAME_BODY_ATTRIBUTE);
        }
        String estimationMessage = messageBody.getText();
        return isolateNumberFromString(estimationMessage);
    }

    private void waitForLetter(){
        driver.switchTo().defaultContent();
        refreshMailboxButton.click();
        driver.switchTo().frame(LETTER_IFRAME_ID);
    }

    @Override
    protected AbstractPage openPage() {
        return null;
    }
}
