package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

// page_url = https://www.google.com/
public class GoogleSearch extends BasePage {

    private final WebDriver driver;

    @FindBy(xpath = ".//textArea[@title='Search']")
    public WebElement searchTextArea;

    @FindBy(xpath = "(.//input[@aria-label='Google Search'])[2]")
    public WebElement googleSearchButton;

    public GoogleSearch(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private final By googleSearchResultsLocator = By.xpath(".//div[@id='search']");

    @Override
    public boolean isPageLoaded() {
        return false;
    }

    @Override
    public void enterSearchTerm(String searchTerm) {
        searchTextArea.sendKeys(searchTerm);
        searchTextArea.sendKeys(Keys.TAB);
    }

    @Override
    public void clickOnSearch() {
        googleSearchButton.click();
    }

    @Override
    public boolean IsSearchReturnsCorrectResults(String expectedResultText) {
        try {
            WebDriverWait wait = new WebDriverWait(this.driver, time);
            List<WebElement> elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(googleSearchResultsLocator));
            return elements.toArray().length > 0;
        } catch (TimeoutException e) {
            System.out.println("Failed to locate the element" + e.fillInStackTrace());
            return false;
        }
    }
}

