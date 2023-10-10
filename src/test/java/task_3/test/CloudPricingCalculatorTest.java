package task_3.test;

import com.epam.training.olha_haichenkova.task_3.driver.DriverSingleton;
import com.epam.training.olha_haichenkova.task_3.model.VirtualMachine;
import com.epam.training.olha_haichenkova.task_3.page.CalculationResultsPage;
import com.epam.training.olha_haichenkova.task_3.page.GoogleCloudMainPage;
import com.epam.training.olha_haichenkova.task_3.service.VirtualMachineCreator;
import com.epam.training.olha_haichenkova.task_3.util.TabsHandler;
import com.epam.training.olha_haichenkova.task_3.page.YopmailMainPage;

import com.epam.training.olha_haichenkova.task_3.util.TestListener;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;


@ExtendWith(TestListener.class)
public class CloudPricingCalculatorTest {

    public WebDriver driver;
    private static final String SEARCH_QUERY = "Google Cloud Pricing Calculator";
    private static final int NUMBER_OF_VM = 4;
    private static final int COMMITTED_USAGE_YEARS = 1;
    private GoogleCloudMainPage googleCloudMainPage;

    @BeforeEach
    public void setUpDriver(){
        driver = DriverSingleton.getDriver();
        googleCloudMainPage = new GoogleCloudMainPage(driver);
    }

    @Test
    public void verifyThatEstimatedPriceInEmailIsEqualToCalculatedOnline(){
        VirtualMachine virtualMachine = VirtualMachineCreator.withCharacteristics();
        CalculationResultsPage calculationResultsPage = googleCloudMainPage
                .openPage()
                .inputSearchQuery(SEARCH_QUERY)
                .openSearchedResult(SEARCH_QUERY)
                .fillInCalculationForm(NUMBER_OF_VM, virtualMachine, COMMITTED_USAGE_YEARS);

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
        DriverSingleton.closeDriver();
    }

}
