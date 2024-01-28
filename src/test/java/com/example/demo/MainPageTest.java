package com.example.demo;

import models.SearchTermData;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.BingSearch;
import pages.GoogleSearch;

public class MainPageTest extends BaseTest {
    private BasePage basePage;

    @DataProvider(name = "testData")
    public SearchTermData[] testData() {
        //User can add additional test Data, could read the test data from external sources
        // like .JSON, Excel, XML or DBs and convert to SearchDataRecord objects
        return new SearchTermData[]{
                new SearchTermData("google","https://www.google.com/",
                        "What is Machine Learning",
                        "Machine learning is used in AI"),
                new SearchTermData("bing","https://www.bing.com/",
                "What is Machine Learning",
                "Machine learning is used in AI")
        };
    }

    //The same test will be executed with multiple data sets.
    @Test(dataProvider = "testData")
    public  void  searchEngineTest(SearchTermData searchTermData){

        //This test follows a concept called AAA - Arrange, Act and Assert

        //Arrange - Launch required Search Engine - ex Google, Bing
        Driver.get(searchTermData.url()); //Navigate to url based on the Test Data Record parameter

        //Test for Google Search
        if(searchTermData.searchEngine() == "google"){
            //Act - Search for a required string
            //1. Create an object of Google search page
            basePage = new GoogleSearch(Driver);
        }
        //Test for Bing Search
        else if (searchTermData.searchEngine() == "bing") {
            basePage = new BingSearch(Driver);
            boolean flag = basePage.isPageLoaded();
        }

        //2. Enter required search term
        basePage.enterSearchTerm(searchTermData.searchTerm());

        //3. Click on the search button
        basePage.clickOnSearch();

        //4. Wait for the page to load
        basePage.waitForPageLoad();

        //Assert - Checks if the required string results any daya
        //5. Check if the search returned any results or not
        boolean flag = basePage.IsSearchReturnsCorrectResults(searchTermData.expectedResult());
        Assert.assertTrue(flag,"Test results does not show the expected text -- " + searchTermData.expectedResult());
    }
}
