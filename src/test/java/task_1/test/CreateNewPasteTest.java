package task_1.test;

import com.epam.training.olha_haichenkova.task_1.page.PastebinCreatedPastePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.epam.training.olha_haichenkova.task_1.page.PastebinHomePage;


public class CreateNewPasteTest {
    public static WebDriver driver;
    public static final String pageTitleEnd = " - Pastebin.com";
    public static final String pasteText1 = "Hello from WebDriver";
    public static final String pasteTitle1 = "helloweb";
    public static final String pasteText2 = """
            git config --global user.name  "New Sheriff in Town"
            git reset $(git commit-tree HEAD^{tree} -m "Legacy code")
            git push origin master --force""";
    public static final String pasteTitle2 = "how to gain dominance among developers";
    private PastebinHomePage pastebinHomePage;

    @BeforeEach
    public void setUpDriver(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        pastebinHomePage = new PastebinHomePage(driver);
    }

    @Test
    public void createNewPasteFor10MinutesTest(){
        String actualText = pastebinHomePage.openPage()
                .inputNewPasteText(pasteText1)
                .selectExpirationTime("10 Minutes")
                .inputNewPasteTitle(pasteTitle1)
                .createNewPaste()
                .readNewPasteText();
        Assertions.assertEquals(pasteText1, actualText, String.format("Text was expected as: %s, but actual is: %s!", pasteText1, actualText));
    }

    @Test
    public void createNewPasteInBashFor10MinutesTest() {
        PastebinCreatedPastePage pastebinCreatedPastePage = pastebinHomePage.openPage()
                .inputNewPasteText(pasteText2)
                .selectSyntaxHighlighting("Bash")
                .selectExpirationTime("10 Minutes")
                .inputNewPasteTitle(pasteTitle2)
                .createNewPaste();

        String actualPageTitle = driver.getTitle();
        Assertions.assertEquals(pasteTitle2 + pageTitleEnd, actualPageTitle, String.format("Title was expected as: %s, but actual is: %s!", pasteTitle2+pageTitleEnd, actualPageTitle));

        String actualSyntax = pastebinCreatedPastePage.readNewPasteSyntax();
        Assertions.assertEquals("Bash", actualSyntax, String.format("Syntax was expected as: %s, but actual is: %s!", "Bash", actualSyntax));

        String actualText = pastebinCreatedPastePage.readNewPasteText();
        Assertions.assertEquals(pasteText2, actualText, String.format("Text was expected as: %s, but actual is: %s!", pasteText2, actualText));
    }

    @AfterEach
    public void tearDownDriver(){
        driver.quit();
    }
}
