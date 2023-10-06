package com.epam.training.olha_haichenkova.task_3.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class GoogleCloudMainPage extends AbstractPage{

    private static final String BASE_URL = "https://cloud.google.com/";
    private static final String RESULTS_CONTAINER = "//div[@class='gsc-expansionArea']";
    private static final String PATH_TO_RESULT = "//div[@class='gsc-thumbnail-inside']//a[contains(@class, 'gs-title')";

    @FindBy(xpath = "//input[@placeholder='Search']")
    private WebElement searchButton;

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

    private static String createSearchResultLocator(String searchQuery){
        String [] searchedWords = searchQuery.split("\\s");
        StringBuilder searchResultLocator = new StringBuilder(PATH_TO_RESULT);
        for (String word : searchedWords) {
            searchResultLocator.append(String.format(" and contains(., '%s')", word));
        }
        searchResultLocator.append("]");
        return searchResultLocator.toString();
    }

    public PricingCalculatorPage openSearchedResult(String searchQuery){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(RESULTS_CONTAINER)));
        driver.findElements(By.xpath(createSearchResultLocator(searchQuery))).get(0).click();
        return new PricingCalculatorPage(driver);
    }

}
