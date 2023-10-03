package com.epam.training.olha_haichenkova.task_3.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PricingCalculatorPage extends AbstractPage{

    private static final String PRICING_CALCULATOR_URL = "https://cloud.google.com/products/calculator";
    static final String OUTER_IFRAME_XPATH = "//main//devsite-iframe/iframe";
    static final String INNER_IFRAME_ID = "myFrame";
    @FindBy(xpath = "//md-tab-item[@id='tab-item-1']")
    private WebElement computeEngineTab;

    @FindBy(xpath = "//input[@ng-model='listingCtrl.computeServer.quantity']")
    private WebElement numberOfServersInput;

    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.computeServer.os']//span[@class='md-select-icon']")
    private WebElement operatingSystemSoftwareSelectIcon;

    @FindBy(xpath = "//md-option[@value='free']")
    private WebElement operatingSystemSoftwareOption;

    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.computeServer.family']//span[@class='md-select-icon']")
    private WebElement vMFamilySelectIcon;

    @FindBy(xpath = "//md-option[@value='gp']")
    private WebElement vMFamilyOption;

    @FindBy(xpath = "//md-select[@name='series']//span[@class='md-select-icon']")
    private WebElement vMSeriesSelectIcon;

    @FindBy(xpath = "//md-option[@value='n1']")
    private WebElement vMSeriesOption;

    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.computeServer.instance']//span[@class='md-select-icon']")
    private WebElement machineTypeSelectIcon;

    @FindBy(xpath = "//md-option[@value='CP-COMPUTEENGINE-VMIMAGE-N1-STANDARD-8']")
    private WebElement machineTypeOption;

    @FindBy(xpath = "//md-checkbox[@ng-model='listingCtrl.computeServer.addGPUs']")
    private WebElement addGPUsCheckbox;

    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.computeServer.gpuType']")
    private WebElement gPUTypeSelectIcon;

    @FindBy(xpath = "//md-option[@value='NVIDIA_TESLA_V100']")
    private WebElement gPUTypeOption;

    @FindBy(xpath = "//md-select[@placeholder='Number of GPUs']//span[@class='md-select-icon']")
    private WebElement numberOfGPUsSelectIcon;

    @FindBy(xpath = "//div[@class='md-select-menu-container md-active md-clickable']//md-option[@value='1']")
    private WebElement numberOfGPUsOption;

    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.computeServer.ssd']//span[@class='md-select-icon']")
    private WebElement localSSDSelectIcon;

    @FindBy(xpath = "//md-option[@value='2']/div[contains(text(), '2x375 GB')]")
    private WebElement localSSDOption;

    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.computeServer.location']//span[@class='md-select-icon']")
    private WebElement datacenterLocationSelectIcon;

    @FindBy(xpath = "//div[@class='md-select-menu-container cpc-region-select md-active md-clickable']//md-option[@value='europe-west3']")
    private WebElement datacenterLocationOption;

    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.computeServer.cud']//span[@class='md-select-icon']")
    private WebElement committedUsageSelectIcon;

    @FindBy(xpath = "//div[@class='md-select-menu-container md-active md-clickable']//md-option[@value='1']")
    private WebElement committedUsageOption;

    @FindBy(xpath = "//form[@name='ComputeEngineForm']//button[contains(.,'Add to Estimate')]")
    private WebElement addToEstimateButton;

    public PricingCalculatorPage(WebDriver driver){
        super (driver);
    }

    private PricingCalculatorPage checkIfComputeEngineSelected(){
        if(!computeEngineTab.isSelected()){
            computeEngineTab.click();
        }
        return this;
    }

    public PricingCalculatorPage selectNumberOfServers(int numberOfServers) {
        numberOfServersInput.sendKeys(""+numberOfServers);
        return this;
    }

    public PricingCalculatorPage selectOperatingSystemSoftware(){
        operatingSystemSoftwareSelectIcon.click();
        if(!operatingSystemSoftwareOption.isSelected()){
            operatingSystemSoftwareOption.click();
        }
        return this;
    }

    public PricingCalculatorPage selectVMClass() {
        vMFamilySelectIcon.click();
        if (!vMFamilyOption.isSelected()) {
            vMFamilyOption.click();
        }
        return this;
    }

    public PricingCalculatorPage selectVMSeries(){
        vMSeriesSelectIcon.click();
        if(!vMSeriesOption.isSelected()){
            vMSeriesOption.click();
        }
        return this;
    }
    public PricingCalculatorPage selectMachineType(){
        machineTypeSelectIcon.click();
        if(!machineTypeOption.isSelected()){
            machineTypeOption.click();
        }
        return this;
    }

    public PricingCalculatorPage selectGPU(){
        addGPUsCheckbox.click();
        gPUTypeSelectIcon.click();
        if(!gPUTypeOption.isSelected()){
            gPUTypeOption.click();
        }
        numberOfGPUsSelectIcon.click();
        wait.until(ExpectedConditions.elementToBeClickable(numberOfGPUsOption));
        if(!numberOfGPUsOption.isSelected()){
            numberOfGPUsOption.click();
        }
        return this;
    }

    public PricingCalculatorPage selectLocalSSD(){
        localSSDSelectIcon.click();
        wait.until(ExpectedConditions.elementToBeClickable(localSSDOption));
        if(!localSSDOption.isSelected()){
            localSSDOption.click();
        }
        return this;
    }

    public PricingCalculatorPage selectDatacenterLocation() {
        datacenterLocationSelectIcon.click();
        wait.until(ExpectedConditions.elementToBeClickable(datacenterLocationOption));
        if (!datacenterLocationOption.isSelected()) {
            datacenterLocationOption.click();
        }
        return this;
    }

    public PricingCalculatorPage selectCommittedUsage() {
        committedUsageSelectIcon.click();
        wait.until(ExpectedConditions.elementToBeClickable(committedUsageOption));
        if (!committedUsageOption.isSelected()) {
            committedUsageOption.click();
        }
        return this;
    }

    public CalculationResultsPage fillInCalculationForm(int numberOfVM) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By
                        .xpath(OUTER_IFRAME_XPATH)));
        WebElement outerIframe = driver.findElement(By.xpath(OUTER_IFRAME_XPATH));
        driver.switchTo().frame(outerIframe);
        driver.switchTo().frame(INNER_IFRAME_ID);

        checkIfComputeEngineSelected()
                .selectNumberOfServers(numberOfVM)
                .selectOperatingSystemSoftware()
                .selectVMClass()
                .selectVMSeries()
                .selectMachineType()
                .selectGPU()
                .selectLocalSSD()
                .selectDatacenterLocation()
                .selectCommittedUsage();

        addToEstimateButton.click();
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(PRICING_CALCULATOR_URL)));
        return new CalculationResultsPage(driver);
    }

    @Override
    protected AbstractPage openPage() {
        return null;
    }
}
