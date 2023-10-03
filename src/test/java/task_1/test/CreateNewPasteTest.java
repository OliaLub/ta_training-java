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
    private static WebDriver driver;
    private static final String PAGE_TITLE_END = " - Pastebin.com";
    private static final String PASTE_TEXT_1 = "Hello from WebDriver";
    private static final String PASTE_TITLE_1 = "helloweb";
    private static final String PASTE_TEXT_2 = """
            git config --global user.name  "New Sheriff in Town"
            git reset $(git commit-tree HEAD^{tree} -m "Legacy code")
            git push origin master --force""";
    private static final String PASTE_TITLE_2 = "how to gain dominance among developers";
    private static final String SYNTAX_HIGHLIGHTING = "Bash";
    private static final String EXPIRATION_TIME ="10 Minutes";
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
                .inputNewPasteText(PASTE_TEXT_1)
                .selectExpirationTime(EXPIRATION_TIME)
                .inputNewPasteTitle(PASTE_TITLE_1)
                .createNewPaste()
                .readNewPasteText();
        Assertions.assertEquals(PASTE_TEXT_1, actualText, String.format("Text was expected as: %s, but actual is: %s!", PASTE_TEXT_1, actualText));
    }

    @Test
    public void createNewPasteInBashFor10MinutesTest() {
        PastebinCreatedPastePage pastebinCreatedPastePage = pastebinHomePage.openPage()
                .inputNewPasteText(PASTE_TEXT_2)
                .selectSyntaxHighlighting(SYNTAX_HIGHLIGHTING)
                .selectExpirationTime(EXPIRATION_TIME)
                .inputNewPasteTitle(PASTE_TITLE_2)
                .createNewPaste();

        String actualPageTitle = driver.getTitle();
        Assertions.assertEquals(PASTE_TITLE_2 + PAGE_TITLE_END, actualPageTitle, String.format("Title was expected as: %s, but actual is: %s!", PASTE_TITLE_2 + PAGE_TITLE_END, actualPageTitle));

        String actualSyntax = pastebinCreatedPastePage.readNewPasteSyntax();
        Assertions.assertEquals(SYNTAX_HIGHLIGHTING, actualSyntax, String.format("Syntax was expected as: %s, but actual is: %s!", "Bash", actualSyntax));

        String actualText = pastebinCreatedPastePage.readNewPasteText();
        Assertions.assertEquals(PASTE_TEXT_2, actualText, String.format("Text was expected as: %s, but actual is: %s!", PASTE_TEXT_2, actualText));
    }

    @AfterEach
    public void tearDownDriver(){
        driver.quit();
    }
}
