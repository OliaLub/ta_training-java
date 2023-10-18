package com.epam.training.olha_haichenkova.task_3.page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class GoogleCloudMainPage extends AbstractPage{

    private static final String BASE_URL = "https://cloud.google.com/";
    private static final String RESULTS_CONTAINER = "//div[@class='gsc-expansionArea']";
    private static final String PATH_TO_RESULT = "//div[@class='gsc-thumbnail-inside']//a[contains(@class, 'gs-title') and contains(., '%s')]";
    private final Logger logger = LogManager.getRootLogger();

    @FindBy(xpath = "//input[@placeholder='Search']")
    private WebElement searchButton;

    public GoogleCloudMainPage(WebDriver driver){
        super (driver);
    }

    @Override
    public GoogleCloudMainPage openPage() {
        driver.get(BASE_URL);
        waitToBeClickable(searchButton);
        logger.info("The page '{}' is opened", BASE_URL);
        return this;
    }

    public GoogleCloudMainPage inputSearchQuery(String searchQuery){
        searchButton.click();
        searchButton.sendKeys(searchQuery, Keys.ENTER);
        return this;
    }

    private static String createSearchResultLocator(String searchQuery){
        return String.format(PATH_TO_RESULT, searchQuery);
    }

    public PricingCalculatorPage openSearchedResult(String searchQuery){
        waitToBePresent(RESULTS_CONTAINER);
        waitToBePresent(createSearchResultLocator(searchQuery)).click();
        logger.info("The link with searched results: '{}' was opened", searchQuery);
        return new PricingCalculatorPage(driver);
    }

}
