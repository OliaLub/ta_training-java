package com.epam.training.olha_haichenkova.task_3.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class GoogleCloudMainPage extends AbstractPage{

    private static final String BASE_URL = "https://cloud.google.com/";
    private static final String RESULTS_CONTAINER = "//div[@class='gsc-expansionArea']";
    @FindBy(xpath = "//input[@placeholder='Search']")
    private WebElement searchButton;

    @FindBy(xpath = "//div[@class='gsc-thumbnail-inside']//a[contains(@class, 'gs-title') and contains(., 'Google') and contains(., 'Cloud') and contains(., 'Pricing') and contains(., 'Calculator')]")
    private List<WebElement> searchResults;

    public GoogleCloudMainPage(WebDriver driver){
        super (driver);
    }

    @Override
    public GoogleCloudMainPage openPage() {
        driver.get(BASE_URL);
        wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        return this;
    }

    public GoogleCloudMainPage inputSearchQuery(String searchQuery){
        searchButton.click();
        searchButton.sendKeys(searchQuery, Keys.ENTER);
        return this;
    }

    public PricingCalculatorPage openSearchedResult(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(RESULTS_CONTAINER)));
        searchResults.get(0).click();
        return new PricingCalculatorPage(driver);
    }

}
