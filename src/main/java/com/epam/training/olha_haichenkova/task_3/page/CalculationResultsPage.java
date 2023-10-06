package com.epam.training.olha_haichenkova.task_3.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.epam.training.olha_haichenkova.task_3.page.PricingCalculatorPage.INNER_IFRAME_ID;
import static com.epam.training.olha_haichenkova.task_3.page.PricingCalculatorPage.OUTER_IFRAME_XPATH;

public class CalculationResultsPage extends AbstractPage{

    private static final String ESTIMATION_RESULT = "//*[@id='resultBlock']//div[@class='cpc-cart-total']";

    @FindBy(xpath = "//*[@id='resultBlock']//div[@class='cpc-cart-total']/h2/b")
    private WebElement totalEstimateOnlineText;

    @FindBy(xpath = "//button[@title='Email Estimate']")
    private WebElement emailEstimateButton;

    @FindBy(xpath = "//form[@name='emailForm']//input[@type='email']")
    private WebElement emailAddressInput;

    @FindBy(xpath = "//md-dialog-actions/button[contains(.,'Send Email')]")
    private WebElement sendEmailButton;

    public CalculationResultsPage(WebDriver driver){
        super (driver);
    }

    public String getTotalEstimate(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(ESTIMATION_RESULT)));
        String totalEstimate = totalEstimateOnlineText.getText();
        return isolateNumberFromString(totalEstimate);
    }

    public CalculationResultsPage openEmailEstimateForm(){
        emailEstimateButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(emailAddressInput)).click();
        return this;
    }

    public CalculationResultsPage fillInEmailEstimateForm(){
        driver.switchTo().defaultContent();
        WebElement outerIframe = driver.findElement(By.xpath(OUTER_IFRAME_XPATH));
        driver.switchTo().frame(outerIframe);
        driver.switchTo().frame(INNER_IFRAME_ID);
        wait.until(ExpectedConditions.elementToBeClickable(emailAddressInput))
                .sendKeys(Keys.chord(Keys.CONTROL, "v"));
        sendEmailButton.click();
        return this;
    }

    @Override
    protected AbstractPage openPage() {
        return null;
    }
}
