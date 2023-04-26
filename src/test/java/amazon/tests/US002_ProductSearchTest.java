package amazon.tests;

import amazon.pages.HomePage;
import amazon.pages.POManager;
import amazon.pages.SearchPage;
import amazon.utils.AmazonUtils;
import amazon.utils.Driver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class US002_ProductSearchTest { // static
    // US002_Product Search - Verify the product search functionality

    //HomePage homePage = new HomePage();
    //SearchPage searchPage = new SearchPage();

    POManager pom = new POManager();

    @Test
    public void TC001_SearchByClickOnSearchIcon() {
        // Verify that after entering search text and clicking on search icon, the search should be performed.

        String searchText = "headphone";
        AmazonUtils.takeMeToSearchPage(pom.getHomePage(), searchText);
        // search page
        String currentUrl = Driver.getDriver().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains(searchText));
    }



    @Test
    public void TC002_CorrectSearchResultsByKeyword() {
        // Verify that the search results should be about the search query.

        String searchText = "Headphones".toLowerCase();

        AmazonUtils.takeMeToSearchPage(pom.getHomePage(), searchText);
        // search page - results
        List<WebElement> products = pom.getSearchPage().searchResults; // product titles

        //  ignore list
        List<String> words = new ArrayList<>() {{
            add("buds");
            add("airpod");
            add("earbuds");
            add("earphones");
        }};

        for (WebElement product : products) {

            boolean isExist = false;

            for (String word : words) {
                if (product.getText().toLowerCase().contains(word)) {
                    isExist = true;
                    break;
                }
            }
            if (isExist) {
                continue;
            }
            if (!product.getText().isEmpty()) {
                // System.out.println(product.getText());
                // System.out.println("---------------------------");
                Assert.assertTrue(product.getText().toLowerCase().contains(searchText));
            }
        }
    }

    @Test
    public void TC003_PresenceFilterSection() {
        // Verify that filter should be present for filtering the search results bases on Brand, Price, Reviews or Ratings.

        AmazonUtils.takeMeToSearchPage(pom.getHomePage(), "aaa");

        Assert.assertTrue(pom.getSearchPage().filterSection.isDisplayed());
        Assert.assertTrue(pom.getSearchPage().brandFilter.isDisplayed());
        Assert.assertTrue(pom.getSearchPage().priceFilter.isDisplayed());
        Assert.assertTrue(pom.getSearchPage().reviewFilter.isDisplayed());
    }

    @Test
    public void TC004_SortingOptionsInFilterPage() {
        // Verify that sorting options should be present and be clickable on search results page.

        AmazonUtils.takeMeToSearchPage(pom.getHomePage(),"aaa"); // String searchText = "aaa";

        // search page
        Assert.assertTrue(pom.getSearchPage().sortOptBtn.isDisplayed());
        Assert.assertTrue(pom.getSearchPage().sortOptBtn.isEnabled());
    }

    @Test
    public void TC005_NumberOfSearchResult() {
        // Verify that the number of search results displayed on one page.

        String searchText = "Headphone";
        AmazonUtils.takeMeToSearchPage(pom.getHomePage(), searchText);

        // 1-48 of 277 results for "aaa"
        String text = pom.getSearchPage().numberOfSearchResult.getText().toLowerCase();

        Assert.assertTrue(!text.isEmpty());
        Assert.assertTrue(text.contains(searchText.toLowerCase()));
    }

    @Test
    public void TC006_PresencePreviousNextButton() {
        // Verify that there should be navigation button(Next and Previous) for navigation to pages
        AmazonUtils.takeMeToSearchPage(pom.getHomePage(), "aaa");
        // search page
        Assert.assertTrue(pom.getSearchPage().previousBtn.isDisplayed());
        Assert.assertTrue(pom.getSearchPage().nextBtn.isDisplayed());
        // System.out.println(searchPage.previousBtn.isEnabled()); // not working

        String prevBtnClass = pom.getSearchPage().previousBtn.getAttribute("class");

        if (prevBtnClass.contains("disabled"))
        {
            System.out.println("Previous btn is disable");
        }
        else
        {
            System.out.println("Previous btn is enable");
        }

    }


    @AfterMethod
    void checkWebsiteForFaking() {
        HomePage mp = new HomePage();
        if (mp.fakeBar.size() != 0) {
            System.out.println("Fake website is opened. It is not my fault. Dont worry");
        } else {
            // System.out.println("There is another problem about the test. Be worry");
        }
    }

}
/*
    System.out.println(product.getText());
    System.out.println("------------------------------------------------------------");
 */

// IMPORTANT NOTES

// The contains() method in Java is case-sensitive
// contains() method is case sensitive
// System.out.println("Headx".contains("head")); // false
// string and list

// List<String> words = Arrays.asList("buds", "airpod", "earbuds", "earphones");
// System.out.println(words.contains("Buds") + " : Buds"); // false : Buds
// System.out.println(words.contains("buds") + " : buds"); // true : buds

// -----------------------------------------------

// The contains() method in Xpath is case sensitive on Text
// //*[contains(text(),'Sponsored')]

// -----------------------------------------------

// Array to ArrayList Method
// List<String> convertedList = Arrays.asList(array);

// -----------------------------------------------

// find the locators in recognito mode
// because the site using ai like amazon uses your browser history, so the website will be different on test automation

//  List<Integer> numbers = new ArrayList<>(Arrays.asList(1,2,3,4,5));
//  System.out.println(numbers);