package amazon.tests;

import amazon.utils.Driver;
import amazon.utils.OutUtils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.qameta.allure.Attachment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class AllureReportListener implements ITestListener { // extends OutUtils

    private static final Logger log = LogManager.getLogger(AllureReportListener.class);

    private static String getTestMethodName(ITestResult result) {
        return result.getMethod().getConstructorOrMethod().getName();
    }

    // Text attachments for Allure
    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshotPNG(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    // Text attachments for Allure
    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }

    // HTML attachments for Allure
    @Attachment(value = "{0}", type = "text/html")
    public static String attachHtml(String html) {
        return html;
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Starting Test Suite '" + context.getName() + "'.......");
        context.setAttribute("WebDriver", Driver.getDriver());
    }

    @Override
    public void onTestStart(ITestResult result) {

        log.info("Testname : " + result.getName());
        System.out.println("I am in onTestStart method " + getTestMethodName(result) + " start");
    }

    @Override
    public void onTestSuccess(ITestResult result) { // public synchronized void
        System.out.println("I am in onTestSuccess method " + getTestMethodName(result) + " succeed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        log.info("On Test Failure : " + result.getName());
        String testName = result.getMethod().getMethodName();
        String destFilePath = OutUtils.takeScreenShotPage(testName);
        String destFullFilePath = OutUtils.takeFullScreenshot(testName);

        System.out.println("Test Method '" + getTestMethodName(result) + "' is Failed");
        if (Driver.getDriver() != null) {
            System.out.println("Screenshot has captured for the Test Method '" + getTestMethodName(result) + "'");
            saveScreenshotPNG(Driver.getDriver());
        }

        // Save a log on allure.
        saveTextLog(getTestMethodName(result) + " failed and screenshot taken!");


    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test Method '" + getTestMethodName(result) + "' is Skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("Test failed but it is in defined success ratio " + getTestMethodName(result));
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("I am in onFinish method " + context.getName());


    }

}

/*
	- TestNg de Listeners methodlarının içinden bir classtaki variable a erişmenin uzun bir yolu var :
		result.getTestClass().getRealClass().getDeclaredField("fieldName").get(result.getInstance());
		Yani :
		result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
 */
// Allure
/*
https://github.com/allure-framework/allure2/releases
Command line server ını pc ye indirip Advanced System Environment da "bin" folder ına kadar olan kismi
Path e ekle.
Sonra allure-reports folder ında cmd
    allure serve
ya da
    allure serve allure-reports
komutları ile açabilrsin.


 */