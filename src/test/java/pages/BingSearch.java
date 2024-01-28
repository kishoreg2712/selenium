package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

// page_url = https://www.google.com/
public class BingSearch extends BasePage{
    private final  WebDriver driver;
    private final  WebDriverWait wait;

    @FindBy(xpath=".//textArea[@id='sb_form_q']")
    public WebElement searchTextArea;

    private final By searchResultsXpath = By.xpath(".//main[@aria-label='Search Results']");

    private final By searchList = By.xpath(".//ul[@id='sa_ul']");

    public BingSearch(WebDriver driver) {
        super(driver);
        this.driver = driver;
        wait = new WebDriverWait(this.driver, time);
        PageFactory.initElements(driver, this);
    }

    @Override
    public boolean isPageLoaded() {
        return true;
    }

    @Override
    public void enterSearchTerm(String searchTerm){
        searchTextArea.sendKeys(searchTerm);
        //Wait for list to show and the press enter

        //Added a sleep intentionally to show the failure for the demo
        try {
            // Sleep for 3 seconds
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println(e.fillInStackTrace());
        }

        //Press enter to perform the search
        searchTextArea.sendKeys(Keys.ENTER);
    }

    @Override
    public void clickOnSearch() {

    }

    @Override
    public boolean IsSearchReturnsCorrectResults(String expectedResultText) {
        try {

            List<WebElement> elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(searchResultsXpath));
            return elements.toArray().length > 0;
        } catch (TimeoutException e) {
            System.out.println("Failed to locate the element" + e.fillInStackTrace());
            return false;
        }
    }
}

