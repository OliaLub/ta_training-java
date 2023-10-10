package task_3.suite;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;
import task_3.test.CloudPricingCalculatorTest;


@Suite
@SelectClasses(CloudPricingCalculatorTest.class)
@SuiteDisplayName("Smoke tests Suite")
public class SmokeTestSuite {
}
