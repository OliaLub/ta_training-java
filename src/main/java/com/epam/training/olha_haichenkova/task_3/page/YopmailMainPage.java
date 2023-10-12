package com.epam.training.olha_haichenkova.task_3.page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class YopmailMainPage extends AbstractPage{

    private static final String YOP_URL = "https://yopmail.com/";
    private static final String YOP_ADVERT_URL_PART = "#google_vignette";

    @FindBy(xpath = "//a[@href='email-generator']/div[@class='txtlien']")
    private WebElement emailGeneratorLink;

    @FindBy(xpath = "//div[@class='tooltip']/button[@id='cprnd']")
    private WebElement copyToClipboardGeneratedAddressButton;

    @FindBy(xpath = "//div[@class='nw']/button[@onclick='egengo();']")
    private WebElement checkInboxButton;

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
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(YOP_URL)));
        if (driver.getCurrentUrl().contains(YOP_ADVERT_URL_PART)){
            driver.navigate().refresh();
            waitToBeClickable(emailGeneratorLink).click();
        }
    }

}
