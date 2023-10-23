package com.epam.training.olha_haichenkova.task_3.page;

import com.epam.training.olha_haichenkova.task_3.page.fragment.pricing_calculator_page.CalculationFormFragment;
import com.epam.training.olha_haichenkova.task_3.page.fragment.pricing_calculator_page.CalculationResultsFragment;
import org.openqa.selenium.WebDriver;


public class PricingCalculatorPage extends AbstractPage{

    public static final String PRICING_CALCULATOR_URL = "https://cloud.google.com/products/calculator";
    public static final String OUTER_IFRAME_XPATH = "//main//devsite-iframe/iframe";
    public static final String INNER_IFRAME_ID = "myFrame";
    private final CalculationFormFragment calculationFormFragment;
    private final CalculationResultsFragment calculationResultsFragment;

    public PricingCalculatorPage(WebDriver driver){
        super (driver);
        calculationFormFragment = new CalculationFormFragment(driver);
        calculationResultsFragment = new CalculationResultsFragment(driver);
    }

    public CalculationFormFragment getCalculationFormFragment() {
        return calculationFormFragment;
    }

    public CalculationResultsFragment getCalculationResultsFragment() {
        return calculationResultsFragment;
    }

    @Override
    protected AbstractPage openPage() {
        return null;
    }
}
