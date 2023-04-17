package amazon.tests;

import amazon.utils.OutUtils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listener implements ITestListener { // extends OutUtils
    ExtentReports extent = OutUtils.getExtentReportObject();

    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    private static final Logger log = LogManager.getLogger(Listener.class);


    @Override
    public void onTestStart(ITestResult result) {

        ExtentTest test = extent.createTest(result.getMethod().getMethodName()); // test name
        extentTest.set(test);
        log.info("Testname : "+ result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.get().log(Status.PASS, "Test passed successfully..");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        log.info("On Test Failure : "+ result.getName());
        String testName = result.getMethod().getMethodName();
        String destFilePath = OutUtils.takeScreenShotPage(testName);

        // E:\selcourse\com.amazon.testng./src/test/resources/images/
        destFilePath = System.getProperty("user.dir")+ destFilePath.substring(1).replace("/","\\");

        extentTest.get().fail(result.getThrowable());
        extentTest.get().addScreenCaptureFromPath(destFilePath, testName);
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}

/*
	- TestNg de Listeners methodlarının içinden bir classtaki variable a erişmenin uzun bir yolu var :
		result.getTestClass().getRealClass().getDeclaredField("fieldName").get(result.getInstance());
		Yani :
		result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
 */