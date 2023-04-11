package amazon.tests;

import amazon.pages.HomePage;
import amazon.pages.SearchPage;
import amazon.utils.AmazonUtils;
import amazon.utils.BrowserUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import org.apache.logging.log4j.*;

public class US004_PaginationTest {

    private static final Logger log = LogManager.getLogger(US004_PaginationTest.class);
    HomePage homePage = new HomePage();
    SearchPage searchPage = new SearchPage();

    @Test
    public void TC001_PreviousDisable() // first page
    {
        String searchText = "headphone";
        AmazonUtils.takeMeToSearchPage(homePage, searchText);
        log.info("We are at search page for " + searchText);

        // search page
        BrowserUtils.scrollToElement(searchPage.previousBtn);
        log.debug("Scroll to previous button..");

        //String tagName = searchPage.previousBtn.getTagName(); // a ise enable, span disable
        String className = searchPage.previousBtn.getAttribute("class"); // s-pagination-item s-pagination-previous s-pagination-disabled

        if (AmazonUtils.IsPaginationEnabled(className)) // previous btn disable
        {
            log.error("Previous button must be disable");
            Assert.assertTrue(false);
        } else {
            log.info("Test passed successfully.");
        }
    }

    @Test
    public void TC002_NextEnable() {
        String searchText = "dress";
        AmazonUtils.takeMeToSearchPage(homePage, searchText);
        log.info("In search page for " + searchText);

        BrowserUtils.scrollToElement(searchPage.nextBtn);
        log.debug("Scrolling is successfully..");

        String className = searchPage.nextBtn.getAttribute("class");
        // String tagName = searchPage.nextBtn.getTagName();

        if (AmazonUtils.IsPaginationEnabled(className) == false) {
            log.error("Test failed...");
            Assert.assertTrue(false);

        }
        log.info("Test passed succesfully..");
    }

    @Test
    public void TC003_PrevNextCheck() {
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
        log.info("Test passed succesfully..");
    }
}
