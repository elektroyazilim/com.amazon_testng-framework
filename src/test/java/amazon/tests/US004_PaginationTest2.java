package amazon.tests;

import amazon.pages.HomePage;
import amazon.pages.POManager;
import amazon.pages.SearchPage;
import amazon.utils.AmazonUtils;
import amazon.utils.BrowserUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class US004_PaginationTest2 {

    private static final Logger log = LogManager.getLogger(US004_PaginationTest2.class);

    POManager pom = new POManager();

    String searchText = "headphone".toLowerCase();

    @AfterMethod
    public void scrollToPageUp()
    {
        BrowserUtils.scrollToUp();
    }

    @Test
    public void TC001_PreviousDisable() // first page
    {
        log.info("We are at search page for " + searchText);

        // search page
        BrowserUtils.scrollToElement(pom.getSearchPage().previousBtn);
        log.debug("Scroll to previous button..");

        //String tagName = searchPage.previousBtn.getTagName(); // a ise enable, span disable
        String className = pom.getSearchPage().previousBtn.getAttribute("class"); // s-pagination-item s-pagination-previous s-pagination-disabled

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
        log.info("In search page for " + searchText);

        BrowserUtils.scrollToElement(pom.getSearchPage().nextBtn);
        log.debug("Scrolling is successfully..");

        String className = pom.getSearchPage().nextBtn.getAttribute("class");
        // String tagName = searchPage.nextBtn.getTagName();

        if (AmazonUtils.IsPaginationEnabled(className) == false) {
            log.error("Test failed...");
            Assert.assertTrue(false);

        }
        log.info("Test passed succesfully..");
    }

    @Test
    public void TC003_PrevNextCheck() {

        // search page

        BrowserUtils.scrollToElement(pom.getSearchPage().previousBtn);
        pom.getSearchPage().anyPage.click();

        BrowserUtils.scrollToElement(pom.getSearchPage().previousBtn);

        String prevClass = pom.getSearchPage().previousBtn.getAttribute("class");
        Assert.assertTrue(AmazonUtils.IsPaginationEnabled(prevClass));

        String nextClass = pom.getSearchPage().nextBtn.getAttribute("class");
        Assert.assertTrue(AmazonUtils.IsPaginationEnabled(nextClass));
        log.info("Test passed succesfully..");
    }
}
