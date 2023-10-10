package task_3.test;

import com.epam.training.olha_haichenkova.task_3.model.VirtualMachine;
import com.epam.training.olha_haichenkova.task_3.page.CalculationResultsPage;
import com.epam.training.olha_haichenkova.task_3.page.GoogleCloudMainPage;
import com.epam.training.olha_haichenkova.task_3.service.VirtualMachineCreator;
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
    private static final String SEARCH_QUERY = "Google Cloud Pricing Calculator";
    private static final int NUMBER_OF_VM = 4;
    private static final String OPERATING_SYSTEM_SOFTWARE = "free";
    private static final String VM_FAMILY = "gp";
    private static final String VM_SERIES = "n1";
    private static final String MACHINE_TYPE = "CP-COMPUTEENGINE-VMIMAGE-N1-STANDARD-8";
    private static final String NUMBER_OF_GPU = "1";
    private static final String GPU_TYPE = "NVIDIA_TESLA_V100";
    private static final String LOCAL_SSD = "2";
    private static final String DATACENTER_LOCATION = "europe-west3";
    private static final int COMMITTED_USAGE_YEARS = 1;
    private GoogleCloudMainPage googleCloudMainPage;

    @BeforeEach
    public void setUpDriver(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        googleCloudMainPage = new GoogleCloudMainPage(driver);
    }

    @Test
    public void verifyThatEstimatedPriceInEmailIsEqualToCalculatedOnline(){
        VirtualMachine virtualMachine = new VirtualMachineCreator()
                .setOperatingSystemSoftware(OPERATING_SYSTEM_SOFTWARE)
                .setVmFamily(VM_FAMILY)
                .setVmSeries(VM_SERIES)
                .setMachineType(MACHINE_TYPE)
                .setgPUType(GPU_TYPE)
                .setNumberOfGPUs(NUMBER_OF_GPU)
                .setLocalSSD(LOCAL_SSD)
                .setDatacenterLocation(DATACENTER_LOCATION)
                .perform();

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
        driver.quit();
    }

}
