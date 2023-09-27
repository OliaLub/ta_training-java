package task_1.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PastebinHomePage extends AbstractPage{
    private final String pastebinURL = "https://pastebin.com/";

    public PastebinHomePage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//textarea[@name='PostForm[text]']")
    private WebElement pasteBodyTextarea;

    @FindBy(xpath = "//div[@class='form-group field-postform-expiration']//span[@class='selection']")
    private WebElement expirationTimeDropdown;

    @FindBy(xpath = "//li[text()='10 Minutes']")
    private WebElement expirationTime10MinutesOption;

    @FindBy(xpath = "//input[@id='postform-name']")
    private WebElement pasteTitleInput;

    @FindBy(xpath = "//button[@type='submit' and @class='btn -big']")
    private WebElement createNewPasteButton;

    @Override
    public PastebinHomePage openPage() {
        driver.get(pastebinURL);
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(pasteBodyTextarea));
        return this;
    }

    public PastebinHomePage inputNewPasteText(String text){
        pasteBodyTextarea.sendKeys(text);
        return this;
    }

    public PastebinHomePage inputNewPasteTitle(String title){
        pasteTitleInput.sendKeys(title);
        return this;
    }

    public PastebinHomePage selectExpirationTime(String optionName) {
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(expirationTimeDropdown)).click();
        WebElement option = driver.findElement(By.xpath(createPathToExpirationTimeOption(optionName)));
        option.click();
        return this;
    }

    private static String createPathToExpirationTimeOption(String optionName){
        String pathToOptionStart = "//ul[@id='select2-postform-expiration-results']/li[text()='";
        String pathToOptionEnd = "']";
        return pathToOptionStart + optionName + pathToOptionEnd;
    }

    public PastebinCreatedPastePage createNewPaste(){
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.elementToBeClickable(createNewPasteButton)).click();
        return new PastebinCreatedPastePage(driver);
    }

}
