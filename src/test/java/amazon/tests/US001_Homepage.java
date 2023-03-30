package amazon.tests;

import amazon.pages.HomePage;
import amazon.utils.Driver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class US001_Homepage { // static

    HomePage homePage = new HomePage();
    String website = "https://www.amazon.com/";
    @Test
    public void TC001_HomepageAccessible()
    {
        // Verify that home page is displayed or not.

        Driver.getDriver().get(website);

        String url = Driver.getDriver().getCurrentUrl();
        String wanted = website.split("www.")[1].substring(0,10);
        // System.out.println(wanted); // amazon.com
        Assert.assertTrue(url.contains(wanted));
    }

    @Test
    public void TC002_PresentSearchFunctionality()
    {
        // Verify that Search Functionality is present on home page.

        Driver.getDriver().get(website);

        WebElement searchBox = homePage.searchBox;
        Assert.assertTrue(searchBox.isDisplayed());

    }

    @Test
    public void TC003_ProductsClickable()
    {
        // Verify that products displayed on home page are clickable.
        Driver.getDriver().get(website);
        List<WebElement> elements = homePage.elements;
        for(WebElement element : elements)
        {
            Assert.assertTrue(element.isEnabled());
            // System.out.println(elements.indexOf(element) + " element");
        }
    }

    @Test
    public void TC004_RedirectProductPage()
    {
        // Verify that when user clicks on a product, user should be redirected to product specification page.
        Driver.getDriver().get(website);
        WebElement anyElement = homePage.elements.get(0);
        anyElement.click();

        String url = Driver.getDriver().getCurrentUrl();
        Assert.assertFalse(url.isEmpty());
    }
}
