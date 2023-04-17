package amazon.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;

import org.apache.logging.log4j.*;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;

public class OutUtils {

    // Screenshots page or element (4.0)
    private static final Logger log = LogManager.getLogger(OutUtils.class);

    public static String takeScreenShotPage(String testName) {
        TakesScreenshot ss = (TakesScreenshot) Driver.getDriver();
        File src = ss.getScreenshotAs(OutputType.FILE);
        String destFile = "./src/test/resources/images/" + testName + ".png";
        try {
            FileUtils.copyFile(src, new File(destFile));
        } catch (Exception ex) {
            System.out.println("Ss Error : " + ex);
        }

        return destFile; // ./src/test/resources/images/abc.png -> relative path
    }

    // Reports, ExtentReport or Allure

    //static ExtentReports extent;

    public static ExtentReports getExtentReportObject() {
        // ExtentReports, ExtentSparkReporter
        String path = System.getProperty("user.dir") + "\\src\\test\\resources\\reports\\extent_index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);

        reporter.config().setReportName("Web Automation Results");
        reporter.config().setDocumentTitle("Test Results");

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Elektroyazilim");

        return extent;
    }


}

// relative path kabul etmiyorsa
// String destFile = System.getProperty("user.dir") + "\\outs\\"+testCaseName + ".png";