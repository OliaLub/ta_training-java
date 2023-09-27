package task_1.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import task_1.page.PastebinHomePage;


public class CreateNewPasteTest {
    public static WebDriver driver;
    public static String pasteText = "Hello from WebDriver";
    public static String pasteTitle = "helloweb";

    public static PastebinHomePage pastebinHomePage;

    @BeforeEach
    public void setUpDriver(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void createNewPasteFor10MinutesTest(){
        pastebinHomePage = new PastebinHomePage(driver);
        String actualText = pastebinHomePage.openPage()
                .inputNewPasteText(pasteText)
                .selectExpirationTime("10 Minutes")
                .inputNewPasteTitle(pasteTitle)
                .createNewPaste()
                .readNewPaste();
        Assertions.assertEquals(pasteText, actualText, "Texts are not equal!");
    }

    @AfterEach
    public void tearDownDriver(){
        driver.quit();
    }
}
