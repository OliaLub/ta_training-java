package com.epam.training.olha_haichenkova.task_1.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class PastebinCreatedPastePage extends AbstractPage{

    @FindBy(xpath = "//ol")
    private WebElement createdPasteText;

    @FindBy(xpath = "//div[@class='info-top']/h1")
    private WebElement createdPasteTitle;

    @FindBy(xpath = " //div[@class='left']/a[contains(@href, '/archive/')]")
    private WebElement createdPasteSyntax;

    public PastebinCreatedPastePage(WebDriver driver){
        super(driver);
    }

    public String readNewPasteText(){
        return wait.until(ExpectedConditions.visibilityOf(createdPasteText)).getText();
    }

    public String readNewPasteTitle(){
        return wait.until(ExpectedConditions.visibilityOf(createdPasteTitle)).getText();
    }

    public String readNewPasteSyntax(){
        return wait.until(ExpectedConditions.visibilityOf(createdPasteSyntax)).getText();
    }

    @Override
    protected AbstractPage openPage() {
        return null;
    }

}
