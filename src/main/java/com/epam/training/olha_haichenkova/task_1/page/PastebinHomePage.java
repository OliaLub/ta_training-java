package com.epam.training.olha_haichenkova.task_1.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class PastebinHomePage extends AbstractPage{
    private static final String pastebinURL = "https://pastebin.com/";
    private static final String pathToOption = "//li[text()='%s']";
    private static final String advertisementPopUpId = "vi-smartbanner";

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
        driver.get(pastebinURL);
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
        WebElement option = driver.findElement(By.xpath(createPathToOption(optionName)));
        option.click();
        return this;
    }

    public PastebinHomePage selectExpirationTime(String optionName) {
        wait.until(ExpectedConditions.elementToBeClickable(expirationTimeDropdown)).click();
        WebElement option = driver.findElement(By.xpath(createPathToOption(optionName)));
        option.click();
        return this;
    }

    private static String createPathToOption(String optionName){
        return String.format(pathToOption, optionName);
    }

    public PastebinCreatedPastePage createNewPaste(){
        closeAdvertisementIfAppear();
        wait.until(ExpectedConditions.elementToBeClickable(createNewPasteButton)).click();
        return new PastebinCreatedPastePage(driver);
    }

    private void closeAdvertisementIfAppear() {
        List<WebElement> advPopUp = driver.findElements(By.id(advertisementPopUpId));
        if (!advPopUp.isEmpty()) {
            closeAdvertisementPopUp();
        }
    }

    private void closeAdvertisementPopUp() {
        advertisementPopUpCloseButton.click();
    }

}
