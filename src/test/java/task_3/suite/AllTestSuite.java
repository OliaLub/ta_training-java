package task_3.suite;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;


@Suite
@SelectPackages("task_3.test")
@SuiteDisplayName("All tests Suite")
public class AllTestSuite {
}
