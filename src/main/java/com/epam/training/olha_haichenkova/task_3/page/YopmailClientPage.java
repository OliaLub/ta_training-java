package com.epam.training.olha_haichenkova.task_3.page;

import com.epam.training.olha_haichenkova.task_3.util.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class YopmailClientPage extends AbstractPage{

    private static final String LETTER_IFRAME_ID = "ifmail";
    private static final String IFRAME_BODY_TAG_NAME = "body";
    private static final String IFRAME_BODY_ATTRIBUTE = "class";
    private static final String IFRAME_BODY_CLASS = "bodymail yscrollbar";
    private static final String MESSAGE_BODY = "//tbody//td/h2";
    private final Logger logger = LogManager.getRootLogger();

    @FindBy(xpath = "//tbody//td/h2")
    private WebElement messageBody;

    @FindBy(tagName = "body")
    private WebElement iframeBody;

    public YopmailClientPage(WebDriver driver) {
        super(driver);
    }

    public String readReceivedEmail() {
        waitToBePresent(By.id(LETTER_IFRAME_ID));
        driver.switchTo().frame(LETTER_IFRAME_ID);
        String bodyClass = iframeBody.getAttribute(IFRAME_BODY_ATTRIBUTE);
        if (!bodyClass.equals(IFRAME_BODY_CLASS)){
            waitForLetter();
        }
        waitToBePresent(MESSAGE_BODY);
        String estimationMessage = messageBody.getText();
        logger.info("The Email with calculation results is read");
        return StringUtil.isolateNumberFromString(estimationMessage);
    }

    private void waitForLetter(){
        driver.navigate().refresh();
        driver.switchTo().frame(LETTER_IFRAME_ID);
        waitToBePresent(By.tagName(IFRAME_BODY_TAG_NAME));
    }

    @Override
    protected AbstractPage openPage() {
        return null;
    }
}
