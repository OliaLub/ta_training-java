package task_3.test;

import com.epam.training.olha_haichenkova.task_3.model.VirtualMachine;
import com.epam.training.olha_haichenkova.task_3.page.GoogleCloudMainPage;
import com.epam.training.olha_haichenkova.task_3.page.YopmailMainPage;
import com.epam.training.olha_haichenkova.task_3.page.fragment.pricing_calculator_page.CalculationResultsFragment;
import com.epam.training.olha_haichenkova.task_3.service.TestDataReader;
import com.epam.training.olha_haichenkova.task_3.service.VirtualMachineCreator;
import com.epam.training.olha_haichenkova.task_3.util.TabsHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class CloudPricingCalculatorTest extends BaseTest{

    private static final String SEARCH_QUERY = "Google Cloud Pricing Calculator";
    private static final int NUMBER_OF_VM = 4;
    private static final String OPERATING_SYSTEM_SOFTWARE = "testdata.operatingSystemSoftware";
    private static final String VM_FAMILY = "testdata.vMFamily";
    private static final String VM_SERIES = "testdata.vmSeries";
    private static final String MACHINE_TYPE = "testdata.machineType";
    private static final String NUMBER_OF_GPU = "testdata.numberOfGPUs";
    private static final String GPU_TYPE = "testdata.gPUType";
    private static final String LOCAL_SSD = "testdata.localSSD";
    private static final String DATACENTER_LOCATION = "testdata.datacenterLocation";
    private static final int COMMITTED_USAGE_YEARS = 1;

    @Test
    public void verifyThatEstimatedPriceInEmailIsEqualToCalculatedOnline(){
        VirtualMachine virtualMachine = new VirtualMachineCreator()
                .setOperatingSystemSoftware(TestDataReader.getTestData(OPERATING_SYSTEM_SOFTWARE))
                .setVmFamily(TestDataReader.getTestData(VM_FAMILY))
                .setVmSeries(TestDataReader.getTestData(VM_SERIES))
                .setMachineType(TestDataReader.getTestData(MACHINE_TYPE))
                .setgPUType(TestDataReader.getTestData(GPU_TYPE))
                .setNumberOfGPUs(TestDataReader.getTestData(NUMBER_OF_GPU))
                .setLocalSSD(TestDataReader.getTestData(LOCAL_SSD))
                .setDatacenterLocation(TestDataReader.getTestData(DATACENTER_LOCATION))
                .perform();

        CalculationResultsFragment calculationResultsFragment = new GoogleCloudMainPage(driver)
                .openPage()
                .inputSearchQuery(SEARCH_QUERY)
                .openSearchedResult(SEARCH_QUERY)
                .getCalculationFormFragment()
                .fillInCalculationForm(NUMBER_OF_VM, virtualMachine, COMMITTED_USAGE_YEARS)
                .getCalculationResultsFragment();

        String actualTotalEstimateSite = calculationResultsFragment.getTotalEstimate();
        calculationResultsFragment.openEmailEstimateForm();

        YopmailMainPage yopmailMainPage = new YopmailMainPage(driver)
                .openPage()
                .generateTemporaryEmail();

        TabsHandler tabsHandler = new TabsHandler(driver);
        tabsHandler.switchToTab(0);
        calculationResultsFragment
                .fillInEmailEstimateForm();

        tabsHandler.switchToTab(1);
        String actualTotalEstimateEmail = yopmailMainPage
                .checkInbox()
                .readReceivedEmail();

        Assertions.assertEquals(actualTotalEstimateSite, actualTotalEstimateEmail,
                String.format("The estimate in EMAIL was expected as: %s, but actual is: %s!",
                        actualTotalEstimateSite, actualTotalEstimateEmail));
    }

}
