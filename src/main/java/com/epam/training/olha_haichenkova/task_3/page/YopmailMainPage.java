package com.epam.training.olha_haichenkova.task_3.page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;


public class YopmailMainPage extends AbstractPage{

    private static final String YOP_URL = "https://yopmail.com/";
    private static final String YOP_ADVERT_URL_PART = "#google_vignette";
    private static final String YOP_OUTER_IFRAME_ID = "aswift_6";
    private static final String YOP_INNER_IFRAME_ID = "ad_iframe";

    private final Logger logger = LogManager.getRootLogger();

    @FindBy(xpath = "//a[@href='email-generator']/div[@class='txtlien']")
    private WebElement emailGeneratorLink;

    @FindBy(xpath = "//div[@class='tooltip']/button[@id='cprnd']")
    private WebElement copyToClipboardGeneratedAddressButton;

    @FindBy(xpath = "//div[@class='nw']/button[@onclick='egengo();']")
    private WebElement checkInboxButton;

    @FindBy(xpath = "//div[@id='dismiss-button']")
    private WebElement closeAdvertisementButton;

    public YopmailMainPage(WebDriver driver){
        super (driver);
    }

    @Override
    public YopmailMainPage openPage() {
        driver.switchTo().newWindow(WindowType.TAB);
        driver.navigate().to(YOP_URL);
        logger.info("The page '{}' is opened", YOP_URL);
        return this;
    }

    public YopmailMainPage generateTemporaryEmail(){
        waitToBeClickable(emailGeneratorLink).click();
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(YOP_URL)));
        closeAdvertisementIfAppear();
        waitToBeClickable(copyToClipboardGeneratedAddressButton).click();
        logger.info("The Temporary Email address is generated");
        return this;
    }

    public YopmailClientPage checkInbox(){
        waitToBeClickable(checkInboxButton).click();
        return new YopmailClientPage(driver);
    }

    private void closeAdvertisementIfAppear() {
        if (driver.getCurrentUrl().contains(YOP_ADVERT_URL_PART)){
            closeAdvertisementPopUp();
        }
    }

    private void closeAdvertisementPopUp() {
        driver.switchTo().frame(YOP_OUTER_IFRAME_ID);
            List<WebElement> closeAdvButton = driver.findElements(By.id("dismiss-button"));
            if (!closeAdvButton.isEmpty()) {
                closeAdvertisementButton.click();
            } else {
                driver.switchTo().frame(YOP_INNER_IFRAME_ID);
                waitToBeClickable(closeAdvertisementButton);
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", closeAdvertisementButton);
            }
            driver.switchTo().defaultContent();
        }

}
