package com.epam.training.olha_haichenkova.task_3.page;

import com.epam.training.olha_haichenkova.task_3.model.VirtualMachine;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static com.epam.training.olha_haichenkova.task_3.page.PricingCalculatorPage.*;

public class CalculationFormFragment extends AbstractFragment{

    private static final String PATH_TO_OPTION = "//md-option[@value='%s']";
    private static final String PATH_TO_DATACENTER_OPTION = "//md-select-menu[@class='_md md-overflow']//md-option[@value='europe-west3']";

    @FindBy(xpath = "//md-tab-item[@id='tab-item-1']")
    private WebElement computeEngineTab;

    @FindBy(xpath = "//input[@ng-model='listingCtrl.computeServer.quantity']")
    private WebElement numberOfServersInput;

    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.computeServer.os']//span[@class='md-select-icon']")
    private WebElement operatingSystemSoftwareSelectIcon;

    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.computeServer.family']//span[@class='md-select-icon']")
    private WebElement vMFamilySelectIcon;

    @FindBy(xpath = "//md-select[@name='series']//span[@class='md-select-icon']")
    private WebElement vMSeriesSelectIcon;

    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.computeServer.instance']//span[@class='md-select-icon']")
    private WebElement machineTypeSelectIcon;

    @FindBy(xpath = "//md-checkbox[@ng-model='listingCtrl.computeServer.addGPUs']")
    private WebElement addGPUsCheckbox;

    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.computeServer.gpuType']")
    private WebElement gPUTypeSelectIcon;

    @FindBy(xpath = "//md-select[@placeholder='Number of GPUs']")
    private WebElement numberOfGPUsSelect;

    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.computeServer.ssd']")
    private WebElement localSSDSelect;

    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.computeServer.location']//span[@class='md-select-icon']")
    private WebElement datacenterLocationSelectIcon;

    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.computeServer.cud']")
    private WebElement committedUsageSelect;

    @FindBy(xpath = "//form[@name='ComputeEngineForm']//button[contains(.,'Add to Estimate')]")
    private WebElement addToEstimateButton;

    public CalculationFormFragment(WebDriver driver){
        super (driver);
    }

    private CalculationFormFragment checkIfComputeEngineSelected(){
        waitToBeClickable(computeEngineTab);
        if(!computeEngineTab.isSelected()){
            computeEngineTab.click();
        }
        return this;
    }

    private CalculationFormFragment selectNumberOfServers(int numberOfServers) {
        numberOfServersInput.sendKeys(""+numberOfServers);
        return this;
    }

    private static String createPathToOption(String optionName){
        return String.format(PATH_TO_OPTION, optionName);
    }

    private static String createPathToOption(String path, String optionName){
        return String.format(path, optionName);
    }

    private CalculationFormFragment selectOperatingSystemSoftware(String optionName){
        operatingSystemSoftwareSelectIcon.click();
        WebElement operatingSystemSoftwareOption = waitToBeClickable(createPathToOption(optionName));
        if(!operatingSystemSoftwareOption.isSelected()){
            operatingSystemSoftwareOption.click();
        }
        return this;
    }

    private CalculationFormFragment selectVMFamily(String optionName) {
        vMFamilySelectIcon.click();
        WebElement vMFamilyOption = waitToBeClickable(createPathToOption(optionName));
        if (!vMFamilyOption.isSelected()) {
            vMFamilyOption.click();
        }
        return this;
    }

    private CalculationFormFragment selectVMSeries(String optionName){
        vMSeriesSelectIcon.click();
        WebElement vMSeriesOption = waitToBeClickable(createPathToOption(optionName));
        if(!vMSeriesOption.isSelected()){
            vMSeriesOption.click();
        }
        return this;
    }

    private CalculationFormFragment selectMachineType(String optionName){
        machineTypeSelectIcon.click();
        WebElement machineTypeOption = waitToBeClickable(createPathToOption(optionName));
        if(!machineTypeOption.isSelected()){
            machineTypeOption.click();
        }
        return this;
    }

    private CalculationFormFragment selectGPU(String gPUTypeOptionName, String numberOfGPUsOptionName){
        addGPUsCheckbox.click();
        gPUTypeSelectIcon.click();
        WebElement gPUTypeOption = waitToBeClickable(createPathToOption(gPUTypeOptionName));
        if(!gPUTypeOption.isSelected()){
            gPUTypeOption.click();
        }
        numberOfGPUsSelect.sendKeys(numberOfGPUsOptionName);
        return this;
    }

    private CalculationFormFragment selectLocalSSD(String optionName){
        localSSDSelect.sendKeys(optionName);
        return this;
    }

    private CalculationFormFragment selectDatacenterLocation(String optionName) {
        datacenterLocationSelectIcon.click();
        WebElement datacenterLocationOption = waitToBeClickable(createPathToOption(PATH_TO_DATACENTER_OPTION, optionName));
        if (!datacenterLocationOption.isSelected()) {
            datacenterLocationOption.click();
        }
        return this;
    }

    private CalculationFormFragment selectCommittedUsage(int committedUsageYears) {
        committedUsageSelect.sendKeys(""+committedUsageYears);
        return this;
    }

    public PricingCalculatorPage fillInCalculationForm(int numberOfVM, VirtualMachine vm, int committedUsageYears) {
        waitToBePresent(OUTER_IFRAME_XPATH);
        WebElement outerIframe = driver.findElement(By.xpath(OUTER_IFRAME_XPATH));
        driver.switchTo().frame(outerIframe);
        driver.switchTo().frame(INNER_IFRAME_ID);

        checkIfComputeEngineSelected()
                .selectNumberOfServers(numberOfVM)
                .selectOperatingSystemSoftware(vm.getOperatingSystemSoftware())
                .selectVMFamily(vm.getVmFamily())
                .selectVMSeries(vm.getVmSeries())
                .selectMachineType(vm.getMachineType())
                .selectGPU(vm.getGPUType(), vm.getNumberOfGPUs())
                .selectLocalSSD(vm.getLocalSSD())
                .selectDatacenterLocation(vm.getDatacenterLocation())
                .selectCommittedUsage(committedUsageYears);

        addToEstimateButton.click();
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(PRICING_CALCULATOR_URL)));
        return new PricingCalculatorPage(driver);
    }

}
