package amazon.tests;

import amazon.pages.HomePage;
import amazon.pages.POManager;
import amazon.pages.SearchPage;
import amazon.utils.AmazonUtils;
import amazon.utils.BrowserUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.List;

public class US003_ProductSearchFilterTest2 {
    // Verify filter section in search product module if it works or not

    private static final Logger log = LogManager.getLogger(US003_ProductSearchFilterTest2.class);

    POManager pom = new POManager();

    String searchText = "headphone".toLowerCase();

    @AfterMethod
    public void scrollToPageUp()
    {
        BrowserUtils.scrollToUp();
    }

    @Test
    public void TC001_PriceFilter() {
        // Verify Price Filter section works or not
        log.info("Search page is opened for " + searchText);

        BrowserUtils.scrollToDown();

        pom.getSearchPage().priceInterval.click();
        BrowserUtils.waitFor(2);

        List<WebElement> prices = pom.getSearchPage().priceResults;

        int min, max;
        String minMax = pom.getSearchPage().twoPrices.getText(); // $25 to $50

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
        // Verify Brand Filter section works or not

        String brand = pom.getSearchPage().brandText.getText();

        // search page
        pom.getSearchPage().anyBrand.click();
        BrowserUtils.waitFor(3);

        for (WebElement element : pom.getSearchPage().productTitles) {
            String productTitle = element.getText();
            Assert.assertTrue(productTitle.contains(brand));
        }

    }

}
