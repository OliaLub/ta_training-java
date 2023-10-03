package task_3.test;

import com.epam.training.olha_haichenkova.task_3.page.CalculationResultsPage;
import com.epam.training.olha_haichenkova.task_3.page.GoogleCloudMainPage;
import com.epam.training.olha_haichenkova.task_3.util.TabsHandler;
import com.epam.training.olha_haichenkova.task_3.page.YopmailMainPage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CloudPricingCalculatorTest {

    public static WebDriver driver;
    private static final String SEARCH_QUERY = "Google Cloud Platform Pricing Calculator";
    private static final int NUMBER_OF_VM = 4;
    private GoogleCloudMainPage googleCloudMainPage;

    @BeforeEach
    public void setUpDriver(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        googleCloudMainPage = new GoogleCloudMainPage(driver);
    }

    @Test
    public void verifyThatEstimatedPriceInEmailIsEqualToCalculatedOnline(){
        CalculationResultsPage calculationResultsPage = googleCloudMainPage
                .openPage()
                .inputSearchQuery(SEARCH_QUERY)
                .openSearchedResult()
                .fillInCalculationForm(NUMBER_OF_VM);

        String actualTotalEstimateSite = calculationResultsPage.getTotalEstimate();
        calculationResultsPage.openEmailEstimateForm();

        YopmailMainPage yopmailMainPage = new YopmailMainPage(driver)
                .openPage()
                .generateTemporaryEmail();

        TabsHandler tabsHandler = new TabsHandler(driver);
        tabsHandler.switchToTab(0);
        calculationResultsPage
                .fillInEmailEstimateForm();

        tabsHandler.switchToTab(1);
        String actualTotalEstimateEmail = yopmailMainPage
                .checkInbox()
                .readReceivedEmail();

        Assertions.assertEquals(actualTotalEstimateSite, actualTotalEstimateEmail,
                String.format("The estimate in EMAIL was expected as: %s, but actual is: %s!",
                        actualTotalEstimateSite, actualTotalEstimateEmail));
    }

    @AfterEach
    public void tearDownDriver(){
        driver.quit();
    }

}
