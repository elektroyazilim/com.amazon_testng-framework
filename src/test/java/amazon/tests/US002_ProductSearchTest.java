package amazon.tests;

import amazon.pages.HomePage;
import amazon.pages.SearchPage;
import amazon.utils.BrowserUtils;
import amazon.utils.Config;
import amazon.utils.Driver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class US002_ProductSearchTest {
    // US002_Product Search - Verify the product search functionality

    HomePage homePage = new HomePage();
    SearchPage searchPage = new SearchPage();

    String website = Config.getProperty("url");

    @Test
    public void TC001_SearchByClickOnSearchIcon() {
        // Verify that after entering search text and clicking on search icon, the search should be performed.

        String searchText = "headphone";
        takeMeToSearchPage(searchText);
        String currentUrl = Driver.getDriver().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains(searchText));
    }

    public void takeMeToSearchPage(String searchText) {
        Driver.getDriver().get(website);
        homePage.searchBox.sendKeys(searchText);
        homePage.searchButton.click();
        BrowserUtils.waitFor(2);
    }

    @Test
    public void TC002_CorrectSearchResultsByKeyword() {
        // Verify that the search results should be about the search query.

        String searchText = "Headphones";
        takeMeToSearchPage(searchText);
        List<WebElement> products = searchPage.searchResults; // product titles

        // buraya ignore listesi gelecek.
        List<String> words = new ArrayList<>(){{
            add("");
            add("buds");
            add("airpod");
        }};


        for (WebElement product : products) {

            if (words.contains(product.getText().toLowerCase())) {
                continue;
            }
            System.out.println(product.getText());
            System.out.println("---------------------------");
            Assert.assertTrue(product.getText().toLowerCase().contains(searchText.toLowerCase()));

        }

    }


    @Test
    public void containTest() // contains() method is case sensitive
    {
        System.out.println("Headx".contains("head")); // false

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