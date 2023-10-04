package task_1.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.epam.training.olha_haichenkova.task_1.page.PastebinHomePage;


public class CreateNewPasteTest {
    private static WebDriver driver;
    private static final String PASTE_TEXT = "Hello from WebDriver";
    private static final String PASTE_TITLE = "helloweb";

    @BeforeEach
    public void setUpDriver(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void createNewPasteFor10MinutesTest(){
        PastebinHomePage pastebinHomePage = new PastebinHomePage(driver);
        String actualText = pastebinHomePage.openPage()
                .inputNewPasteText(PASTE_TEXT)
                .selectExpirationTime("10 Minutes")
                .inputNewPasteTitle(PASTE_TITLE)
                .createNewPaste()
                .readNewPaste();
        Assertions.assertEquals(PASTE_TEXT, actualText, "Texts are not equal!");
    }

    @AfterEach
    public void tearDownDriver(){
        driver.quit();
    }
}
