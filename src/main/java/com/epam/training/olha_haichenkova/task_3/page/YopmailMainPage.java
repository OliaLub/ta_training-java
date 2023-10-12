package com.epam.training.olha_haichenkova.task_3.page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;


public class YopmailMainPage extends AbstractPage{

    private static final String YOP_URL = "https://yopmail.com/";
    private static final String YOP_ADVERT_URL_PART = "#google_vignette";
    private static final String YOP_OUTER_IFRAME_ID = "aswift_6";
    private static final String YOP_INNER_IFRAME_ID = "ad_iframe";

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
        return this;
    }

    public YopmailMainPage generateTemporaryEmail(){
        waitToBeClickable(emailGeneratorLink).click();
        closeAdvertisementIfAppear();
        waitToBeClickable(copyToClipboardGeneratedAddressButton).click();
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
        try{
            closeAdvertisementButton.click();
        } catch (NoSuchElementException e){
            driver.switchTo().frame(YOP_INNER_IFRAME_ID);
            closeAdvertisementButton.click();
        }
        driver.switchTo().defaultContent();
    }

}
