package amazon.tests;

import amazon.pages.HomePage;
import amazon.pages.SearchPage;
import amazon.utils.AmazonUtils;
import amazon.utils.BrowserUtils;
import amazon.utils.Driver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class US003_ProductSearchFilterTest {

    HomePage homePage = new HomePage();
    SearchPage searchPage = new SearchPage();

    @Test
    public void TC001_PriceFilter() {
        String searchText = "dress";
        AmazonUtils.takeMeToSearchPage(homePage, searchText);

        BrowserUtils.scrollToDown();

        searchPage.priceInterval.click();
        BrowserUtils.waitFor(2);

        List<WebElement> prices = searchPage.priceResults;

        int min, max;
        String minMax = searchPage.twoPrices.getText(); // $25 to $50

        minMax = minMax.replace('$', ' ');

        min = Integer.parseInt(minMax.split("to")[0].trim()); // 25
        max = Integer.parseInt(minMax.split("to")[1].trim()); // 50

        for (WebElement price : prices) {
            int newPrice = Integer.parseInt(price.getText().split("\\n")[0].substring(1));
            // System.out.println(newPrice);
            // System.out.println("-----");

            if (!(newPrice >= min && newPrice <= max)) {
                System.out.println("invalid");
                Assert.assertTrue(false, "The price is out of border");
            }

        }

    }

    @Test
    public void TC002_BrandFilter() {

    }

}
