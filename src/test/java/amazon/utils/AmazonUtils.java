package amazon.utils;

import amazon.pages.HomePage;

public class AmazonUtils {
    static String website = Config.getProperty("url");
    public static void takeMeToSearchPage(HomePage homePage, String searchText) {
        Driver.getDriver().get(website);
        homePage.searchBox.sendKeys(searchText);
        homePage.searchButton.click();
        BrowserUtils.waitFor(3);
    }

    public static boolean IsPaginationEnabled(String className)
    {
        if(className.contains("s-pagination-disabled")) // tagName.equals("span")
        {
            return false;
        }
        else
        {
           return true;
        }
    }
}
