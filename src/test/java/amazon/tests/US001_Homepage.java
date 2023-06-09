package amazon.tests;

import amazon.pages.HomePage;
import amazon.pages.POManager;
import amazon.utils.Config;
import amazon.utils.Driver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.List;

public class US001_Homepage { // static

    // US001_Verify the details on Home page
    // HomePage homePage = new HomePage();
    POManager pom = new POManager();
    String website = Config.getProperty("url");

    @Test
    public void TC001_HomepageAccessible() {
        // Verify that home page is displayed or not.

        Driver.getDriver().get(website);

        String url = Driver.getDriver().getCurrentUrl();
        String wanted = website.split("www.")[1].substring(0, 10);
        // System.out.println(wanted); // amazon.com
        Assert.assertTrue(url.contains(wanted));
    }

    @Test
    public void TC002_PresentSearchFunctionality() {
        // Verify that Search Functionality is present on home page.

        Driver.getDriver().get(website);

        WebElement searchBox = pom.getHomePage().searchBox;
        Assert.assertTrue(searchBox.isDisplayed());

    }

    @Test
    public void TC003_ProductsClickable() {
        // Verify that products displayed on home page are clickable.
        Driver.getDriver().get(website);
        List<WebElement> elements = pom.getHomePage().products;
        for (WebElement element : elements) {
            Assert.assertTrue(element.isEnabled());
            // System.out.println(elements.indexOf(element) + " element");
        }
    }

    @Test
    public void TC004_RedirectProductPage() {
        // Verify that when user clicks on a product, user should be redirected to product specification page.
        Driver.getDriver().get(website);
        WebElement anyElement = pom.getHomePage().products.get(0);

        String firstProductUrl = pom.getHomePage().firstProductInHome.getAttribute("href");
        anyElement.click();

        // BrowserUtils.waitFor(1);
        String url = Driver.getDriver().getCurrentUrl();
        Assert.assertTrue(url.contains(firstProductUrl));
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
