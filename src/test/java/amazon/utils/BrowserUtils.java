package amazon.utils;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import javax.annotation.Nullable;
import java.io.*;
import java.time.Duration;
import java.util.*;


public class BrowserUtils {

    public static void waitFor(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // explicit wait, visibility of element, condition
    public static WebElement waitForVisibility(WebElement element, int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeToWaitInSec));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    // explicit wait, visibility of element, condition
    public static WebElement waitForVisibility(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    //------- dikkat tam olarak calismiyor olabilir--------getLocatorFromWebElement() ----tarafından response degismis olabilir---------
    public static void waitForPresenceOfLocatedElement(WebElement element, int timeOutInSecond) {
        //sanki oldu gibi arada kontrol et. DataTableListMap.feature daki kodlari kullanabilirsin
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeOutInSecond));
        By locator = BrowserUtils.getLocatorFromWebElement(element);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        // this mehod uses below method
    }

    private static By getLocatorFromWebElement(WebElement element) {
        //[[ChromeDriver: chrome on WINDOWS (421ef25173cc20ec4a7cd08cd2bef8b3)] -> xpath: //*[contains(text(), 'Video Conferencing - Telehealth')]]
        //**** css ve xpath icin calisti
        By by = null;
        //[[ChromeDriver: chrome on XP (d85e7e220b2ec51b7faf42210816285e)] -> xpath: //input[@title='Search']]
        String[] pathVariables = (element.toString().split("->")[1].replaceFirst("(?s)(.*)\\]", "$1" + "")).split(":");

        String selector = pathVariables[0].trim();
        String value = pathVariables[1].trim();

        switch (selector) {
            case "id":
                by = By.id(value);
                break;
            case "className":
                by = By.className(value);
                break;
            case "tagName":
                by = By.tagName(value);
                break;
            case "xpath":
                by = By.xpath(value);
                break;
            case "css selector": //cssSelector
                by = By.cssSelector(value);
                break;
            case "linkText":
                by = By.linkText(value);
                break;
            case "name":
                by = By.name(value);
                break;
            case "partialLinkText":
                by = By.partialLinkText(value);
                break;
            default:
                throw new IllegalStateException("locator : " + selector + " not found!!!");
        }
        return by;

    } //--------- dikkat tam olarak calismiyor olabilir----------getLocatorFromWebElement() ----tarafından response degismis olabilir---------

    //********** waitforclickability **********
    public static WebElement waitForClickability(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static WebElement waitForClickability(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    } //********************************


    // Her site icin calismiyor sebebi ise "Marionette	WARN	Ignoring event 'DOMContentLoaded' because document has an invalid readyState of 'complete'":
    public static void waitForPageToLoad(long timeOutInSeconds) {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        try {
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeOutInSeconds));
            wait.until(expectation);
        } catch (Throwable error) {
            error.printStackTrace();
        }
    }

    //***** isDisplayed() metodu customize edildi ***************************************
/*
    public static void verifyElementDisplayed(By by) {
        try {
            Assert.assertTrue("Element not visible: " + by, Driver.getDriver().findElement(by).isDisplayed());
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            Assert.fail("Element not found: " + by);

        }
    }

    public static void verifyElementDisplayed(WebElement element) {
        try {
            Assert.assertTrue("Element not visible: " + element, element.isDisplayed());
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            Assert.fail("Element not found: " + element);

        }
    }
    public static void verifyElementNotDisplayed(By by) {
        try {
            Assert.assertFalse("Element should not be visible: " + by, Driver.getDriver().findElement(by).isDisplayed());
        } catch (NoSuchElementException e) {
            e.printStackTrace();

        }
    }

    public static void verifyElementNotDisplayed(WebElement element) {
        try {
            Assert.assertFalse("Element should not be visible: " + element, element.isDisplayed());
        } catch (NoSuchElementException e) {
            e.printStackTrace();

        }
    } //***** isDisplayed() metodu customize edildi ***************************************


 */
    public static void switchToWindow(String targetTitle) {
        String origin = Driver.getDriver().getWindowHandle();
        for (String handle : Driver.getDriver().getWindowHandles()) {
            Driver.getDriver().switchTo().window(handle);
            if (Driver.getDriver().getTitle().equals(targetTitle)) {
                return;
            }
        }
        Driver.getDriver().switchTo().window(origin);
    }

    // randomly generate an email method
    public static String generateAnEmail() {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        Random rnd = new Random();
        StringBuilder email = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            email.append(alphabet.charAt(rnd.nextInt(alphabet.length())));
        }
        return email + "@gmail.com";
    }

    //****************************************************//
    public static List<String> getElementsText(List<WebElement> list) {
        List<String> elemTexts = new ArrayList<>();
        for (WebElement el : list) {
            elemTexts.add(el.getText());
        }
        return elemTexts;
    }


    public static List<String> getElementsText(By locator) {

        List<WebElement> elems = Driver.getDriver().findElements(locator);
        List<String> elemTexts = new ArrayList<>();

        for (WebElement el : elems) {
            elemTexts.add(el.getText());
        }
        return elemTexts;
    }


    // My method - return all open windows urls except mainpage - my method -EsmaY
    public static ArrayList<String> getAllWindowsUrls() {
        Set<String> windows = Driver.getDriver().getWindowHandles();
        Iterator<String> it = windows.iterator();
        String parent = it.next();
        ArrayList<String> urls = new ArrayList<>();// can be title
        for (int i = 1; i < windows.size(); i++) {
            String childId = it.next();

            Driver.getDriver().switchTo().window(childId);
            urls.add(Driver.getDriver().getCurrentUrl());
        }
        return urls;
    }

    // My method - getElementFromGetText() - new version - my method -EsmaY
    public static WebElement getElementFromGetText(String text) {
        By element = By.xpath("//*[contains(text(), '" + text + "')]");
        WebElement found = null;
        try {
            List<WebElement> elements = Driver.getDriver().findElements(element);
            found = Driver.getDriver().findElement(element);
            if (elements.size() != 1) {
                System.out.println(elements.size() + " element found. Check if the element is yours");
            }
            return found;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            System.out.println("Element not found");
            return null;
        }
    }

    // My method - verifyVideoPlays() - youtube video is play - my method -EsmaY
    public static void verifyVideoPlays(WebElement element) {
        //precond: user clicks on youtube video
        // can be added iframe section and clicks
        try {
            BrowserUtils.waitFor(1); // time to play
            String playTime = element.getAttribute("currentTime");
            if (Double.parseDouble(playTime) > 0) {
                Assert.assertTrue(true);
            } else {
                Assert.assertTrue(false);
            }
        } catch (Exception ex) {
            Assert.fail("You didnt click on play button.");
        }
    }

    // My method - getChildTitle() - get Child Window Title - my method -EsmaY
    public static String getChildTitle() {
        Set<String> windows = Driver.getDriver().getWindowHandles();
        Iterator<String> it = windows.iterator();
        String parent = it.next();
        String child = it.next();
        Driver.getDriver().switchTo().window(child);
        String pageTitle = Driver.getDriver().getTitle();
        Driver.getDriver().close();
        //Driver.getDriver().switchTo().window(parent); -> gerek yok
        return pageTitle;
    }

    // My method - getChildWindowURL() - my method -EsmaY
    public static String getChildWindowURL() {
        Set<String> windows = Driver.getDriver().getWindowHandles();
        Iterator<String> it = windows.iterator();
        String main = Driver.getDriver().getWindowHandle();
        it.next();
        String child = it.next();
        Driver.getDriver().switchTo().window(child);
        String childUrl = Driver.getDriver().getCurrentUrl();
        Driver.getDriver().close();
        BrowserUtils.waitFor(3);
        Driver.getDriver().switchTo().window(main);
        //Driver.getDriver().switchTo().window(parent); -> hata
        return childUrl;
    }

    // My Method - getChildTitle()
    public static String getChildTitle(int windowSeq) {
        Set<String> windows = Driver.getDriver().getWindowHandles();
        Iterator<String> it = windows.iterator();
        String parent = it.next();
        String child;
        String pageTitle = "";
        for (int i = 1; i < windows.size(); i++) {
            child = it.next();
            if (windowSeq == i) {
                Driver.getDriver().switchTo().window(child);
                pageTitle = Driver.getDriver().getTitle();
                Driver.getDriver().switchTo().window(parent);
                //driver.getCurrentUrl();
                break;
            }
        }
        return pageTitle;
    }
    //==================================================================

    //********** Actions class ***********
    public static void doubleClick(WebElement element) {
        new Actions(Driver.getDriver()).doubleClick(element).build().perform();
    }

    //****** Javascript metotları *******************************

    public static void clickWithJS(WebElement element) {
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].click();", element);
    }

    public static void scrollToElement(WebElement element) {
        //scrollIntoView() not working for horizontal scroll
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void scrollToUp() {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("scroll(0, 250);");
        //js.executeScript("scroll(0, -250);"); -> up
    }

    public static void scrollUntilEnd(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public static void clickXYByJs() {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        String jsCode = "const simulateClick = (x, y) => {\n" +
                "  const event = new MouseEvent('click', {\n" +
                "    view: window,\n" +
                "    bubbles: true,\n" +
                "    cancelable: true,\n" +
                "    screenX: x,\n" +
                "    screenY: y\n" +
                "  })\n" +
                "\n" +
                "  const element = document.elementFromPoint(x, y)\n" +
                "  element.dispatchEvent(event)\n" +
                "}\n" +
                "\n" +
                "simulateClick(32, 32)";

        js.executeScript(jsCode);
    }
    /*        js.executeScript(
                const simulateClick = (x, y) => {
  				const event = new MouseEvent('click', {
                    view: window,
                    bubbles: true,
                    cancelable: true,
                    screenX: x,
                    screenY: y
  		})

  		const element = document.elementFromPoint(x, y)
            element.dispatchEvent(event)
        }

        simulateClick(32, 32)*/


    //to get the page title with JS
    public static String getTitleByJS() {
        JavascriptExecutor jsexecutor = ((JavascriptExecutor) Driver.getDriver());
        String title = jsexecutor.executeScript("return document.title;").toString();
        return title;
    }

    // clear textbox by javascript
    public static void clearTxtboxByJs(WebElement element)
    {
        JavascriptExecutor js = (JavascriptExecutor)Driver.getDriver();
        js.executeScript("arguments[0].value = '';", element);
    }

    //------------asagıdaki js kodlarini check et ------------------------

    public static void changeColor(String color, WebElement element) {
        JavascriptExecutor javascriptExecutor = ((JavascriptExecutor) Driver.getDriver());
        javascriptExecutor.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Flashing teh background color
    public static void flash(WebElement element) {
        String bgColor = element.getCssValue("backgroundcolor");
        for (int i = 0; i < 10; i++) {
            changeColor("rgb(0,200,0", element);
            changeColor(bgColor, element);
        }
    }

    //this willg enerate an alert when needed
    public static void generateAlert(String message) throws InterruptedException {
        JavascriptExecutor javascriptExecutor = ((JavascriptExecutor) Driver.getDriver());
        javascriptExecutor.executeScript("alert('" + message + "')");
        Thread.sleep(3000);
    }

    /*
     * executes the given JavaScript command on given web element
     */
    public static void executeJScommand(WebElement element, String command) {
        JavascriptExecutor jse = (JavascriptExecutor) Driver.getDriver();
        jse.executeScript(command, element);
    }

    /*
     * executes the given JavaScript command on given web element
     */
    public static void executeJScommand(String command) {
        JavascriptExecutor jse = (JavascriptExecutor) Driver.getDriver();
        jse.executeScript(command);
    }

    //****** Javascript metotları *******************************

}
