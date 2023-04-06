package amazon.tests;

import amazon.pages.HomePage;
import amazon.pages.SearchPage;
import amazon.utils.AmazonUtils;
import amazon.utils.BrowserUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class US004_PaginationTest {
    HomePage homePage = new HomePage();
    SearchPage searchPage = new SearchPage();

    @Test
    public void TC001_PreviousDisable() // first page
    {
        String searchText = "headphone";
        AmazonUtils.takeMeToSearchPage(homePage,searchText);

        // search page
        BrowserUtils.scrollToElement(searchPage.previousBtn);

        //String tagName = searchPage.previousBtn.getTagName(); // a ise enable, span disable
        String className = searchPage.previousBtn.getAttribute("class"); // s-pagination-item s-pagination-previous s-pagination-disabled

        if(AmazonUtils.IsPaginationEnabled(className)) // previous btn disable
        {
            Assert.assertTrue(false);
        }
    }
    @Test
    public void TC002_NextEnable()
    {
        String searchText = "dress";
        AmazonUtils.takeMeToSearchPage(homePage,searchText);

        BrowserUtils.scrollToElement(searchPage.nextBtn);

        String className = searchPage.nextBtn.getAttribute("class");
        // String tagName = searchPage.nextBtn.getTagName();

        if (AmazonUtils.IsPaginationEnabled(className) == false)
        {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void TC003_PrevNextCheck()
    {
        String anyText = "dress";
        AmazonUtils.takeMeToSearchPage(homePage, anyText);

        // search page

        BrowserUtils.scrollToElement(searchPage.previousBtn);
        searchPage.anyPage.click();

        BrowserUtils.scrollToElement(searchPage.previousBtn);

        String prevClass = searchPage.previousBtn.getAttribute("class");
        Assert.assertTrue(AmazonUtils.IsPaginationEnabled(prevClass));

        String nextClass = searchPage.nextBtn.getAttribute("class");
        Assert.assertTrue(AmazonUtils.IsPaginationEnabled(nextClass));
    }
}
