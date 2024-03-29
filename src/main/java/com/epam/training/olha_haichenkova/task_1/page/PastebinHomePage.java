package com.epam.training.olha_haichenkova.task_1.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class PastebinHomePage extends AbstractPage{
    private static final String PASTEBIN_URL = "https://pastebin.com/";
    private static final String PATH_TO_OPTION = "//li[text()='%s']";
    private static final String ADVERTISEMENT_POP_UP_ID = "vi-smartbanner";

    @FindBy(xpath = "//textarea[@name='PostForm[text]']")
    private WebElement pasteBodyTextarea;

    @FindBy(xpath = "//div[@class='form-group field-postform-format']//span[@class='selection']")
    private WebElement syntaxHighlightingDropdown;

    @FindBy(xpath = "//li[text()='Bash']")
    private WebElement syntaxHighlightingBashOption;

    @FindBy(xpath = "//div[@class='form-group field-postform-expiration']//span[@class='selection']")
    private WebElement expirationTimeDropdown;

    @FindBy(xpath = "//li[text()='10 Minutes']")
    private WebElement expirationTime10MinutesOption;

    @FindBy(xpath = "//input[@id='postform-name']")
    private WebElement pasteTitleInput;

    @FindBy(xpath = "//button[@type='submit' and @class='btn -big']")
    private WebElement createNewPasteButton;

    @FindBy(xpath = "//vli[@class='vliIgnore']")
    private WebElement advertisementPopUpCloseButton;

    public PastebinHomePage(WebDriver driver){
        super(driver);
    }

    @Override
    public PastebinHomePage openPage() {
        driver.get(PASTEBIN_URL);
       wait.until(ExpectedConditions.elementToBeClickable(pasteBodyTextarea));
        return this;
    }

    public PastebinHomePage inputNewPasteText(String text){
        pasteBodyTextarea.sendKeys(text);
        new Actions(driver).scrollToElement(createNewPasteButton).perform();
        return this;
    }

    public PastebinHomePage inputNewPasteTitle(String title){
        pasteTitleInput.sendKeys(title);
        return this;
    }

    public PastebinHomePage selectSyntaxHighlighting(String optionName) {
        wait.until(ExpectedConditions.elementToBeClickable(syntaxHighlightingDropdown)).click();
        driver.findElement(By.xpath(createPathToOption(optionName))).click();
        return this;
    }

    public PastebinHomePage selectExpirationTime(String optionName) {
        wait.until(ExpectedConditions.elementToBeClickable(expirationTimeDropdown)).click();
        driver.findElement(By.xpath(createPathToOption(optionName))).click();
        return this;
    }

    private static String createPathToOption(String optionName){
        return String.format(PATH_TO_OPTION, optionName);
    }

    public PastebinCreatedPastePage createNewPaste(){
        closeAdvertisementIfAppear();
        wait.until(ExpectedConditions.elementToBeClickable(createNewPasteButton)).click();
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(PASTEBIN_URL)));
        return new PastebinCreatedPastePage(driver);
    }

    private void closeAdvertisementIfAppear() {
        WebElement advPopUp = driver.findElement(By.id(ADVERTISEMENT_POP_UP_ID));
        if (advPopUp.isDisplayed()) {
            closeAdvertisementPopUp();
        }
    }

    private void closeAdvertisementPopUp() {
        advertisementPopUpCloseButton.click();
    }

}
