package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

// page_url = about:blank
public abstract class BasePage {
    private final  WebDriver driver;
    public final static Duration time = Duration.ofSeconds(30);

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public abstract boolean isPageLoaded();

    public abstract void enterSearchTerm(String searchTerm);

    public abstract void clickOnSearch();

    public abstract boolean IsSearchReturnsCorrectResults(String expectedResultText);

    public void waitForPageLoad(){
        WebDriverWait wait = new WebDriverWait(driver, time); // 10 seconds timeout
        // Use ExpectedConditions to wait until the page is loaded
        wait.until(ExpectedConditions.jsReturnsValue("return document.readyState == 'complete';"));
    }

    public void clickOnEmptyArea() {
        // Create an instance of the Actions class
        Actions actions = new Actions(this.driver);

        // Find any element on the page (you can use body or html)
        WebElement anyElement = driver.findElement(By.tagName("body"));

        // Move the mouse to the element (in this case, anyElement)
        actions.moveToElement(anyElement);

        // Perform a click action
        actions.click().build().perform();
    }
}