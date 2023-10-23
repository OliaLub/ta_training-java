package task_3.test;

import com.epam.training.olha_haichenkova.task_3.driver.DriverSingleton;
import com.epam.training.olha_haichenkova.task_3.util.CustomTestWatcher;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;


@ExtendWith(CustomTestWatcher.class)
public class BaseTest {

    public WebDriver driver;

    @BeforeEach
    public void setUpDriver(){
        driver = DriverSingleton.getDriver();
    }

    @AfterEach
    public void tearDownDriver(){
        DriverSingleton.closeDriver();
    }

}
