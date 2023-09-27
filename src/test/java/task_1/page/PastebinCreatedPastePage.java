package task_1.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PastebinCreatedPastePage extends AbstractPage{

    @FindBy(xpath = "//ol[@class='text']//div")
    private WebElement createdPasteText;

    public PastebinCreatedPastePage(WebDriver driver){
        super(driver);
    }

    public String readNewPaste(){
        return new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.visibilityOf(createdPasteText)).getText();
    }

    @Override
    protected AbstractPage openPage() {
        return null;
    }

}
