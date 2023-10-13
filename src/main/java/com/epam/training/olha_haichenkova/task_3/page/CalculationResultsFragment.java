package com.epam.training.olha_haichenkova.task_3.page;

import com.epam.training.olha_haichenkova.task_3.util.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.epam.training.olha_haichenkova.task_3.page.PricingCalculatorPage.*;


public class CalculationResultsFragment extends AbstractFragment{

    private static final String ESTIMATION_RESULT = "//*[@id='resultBlock']//div[@class='cpc-cart-total']";
    private final Logger logger = LogManager.getRootLogger();

    @FindBy(xpath = "//*[@id='resultBlock']//div[@class='cpc-cart-total']/h2/b")
    private WebElement totalEstimateOnlineText;

    @FindBy(xpath = "//button[@title='Email Estimate']")
    private WebElement emailEstimateButton;

    @FindBy(xpath = "//form[@name='emailForm']//input[@type='email']")
    private WebElement emailAddressInput;

    @FindBy(xpath = "//md-dialog-actions/button[contains(.,'Send Email')]")
    private WebElement sendEmailButton;

    public CalculationResultsFragment(WebDriver driver){
        super (driver);
    }

    public String getTotalEstimate(){
        waitToBePresent(ESTIMATION_RESULT);
        String totalEstimate = totalEstimateOnlineText.getText();
        return StringUtil.isolateNumberFromString(totalEstimate);
    }

    public CalculationResultsFragment openEmailEstimateForm(){
        emailEstimateButton.click();
        waitToBeClickable(emailAddressInput).click();
        return this;
    }

    public CalculationResultsFragment fillInEmailEstimateForm(){
        WebElement outerIframe = driver.findElement(By.xpath(OUTER_IFRAME_XPATH));
        driver.switchTo().frame(outerIframe);
        driver.switchTo().frame(INNER_IFRAME_ID);
        waitToBeClickable(emailAddressInput).sendKeys(Keys.chord(Keys.CONTROL, "v"));
        sendEmailButton.click();
        logger.info("The Email with calculation results is sent");
        return this;
    }

}
