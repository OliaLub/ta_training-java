package com.epam.training.olha_haichenkova.task_3.page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;


public class YopmailMainPage extends AbstractPage{

    private static final String YOP_URL = "https://yopmail.com/";
    private static final String YOP_OUTER_IFRAME_ID = "aswift_6";
    private static final String YOP_INNER_IFRAME_ID = "ad_iframe";

    @FindBy(xpath = "//a[@href='email-generator']/div[@class='txtlien']")
    private static WebElement emailGeneratorLink;

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
        return this;
    }

    public YopmailMainPage generateTemporaryEmail(){
        wait.until(ExpectedConditions.elementToBeClickable(emailGeneratorLink)).click();
        closeAdvertisementIfAppear();
        wait.until(ExpectedConditions.elementToBeClickable(copyToClipboardGeneratedAddressButton)).click();
        return this;
    }

    public YopmailClientPage checkInbox(){
        wait.until(ExpectedConditions.elementToBeClickable(checkInboxButton)).click();
        return new YopmailClientPage(driver);
    }

    private void closeAdvertisementIfAppear() {
        List<WebElement> advPopUp = driver.findElements(By.id(YOP_OUTER_IFRAME_ID));
        if (!advPopUp.isEmpty()) {
            closeAdvertisementPopUp();
        }
    }

    private void closeAdvertisementPopUp() {
        driver.switchTo().frame(YOP_OUTER_IFRAME_ID);
        driver.switchTo().frame(YOP_INNER_IFRAME_ID);
        closeAdvertisementButton.click();
        driver.switchTo().defaultContent();
    }

}
