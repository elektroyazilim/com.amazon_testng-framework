package amazon.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;

import org.apache.logging.log4j.*;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

        return relativeToAbsolutePath("src/test/resources/images", testName + ".png"); // ./src/test/resources/images/abc.png -> relative path
    }

    public static String takeFullScreenshot(String fileName)//Ashot
    {
        Screenshot screenshot = new AShot()
                .shootingStrategy(ShootingStrategies.viewportPasting(100))
                .takeScreenshot(Driver.getDriver());

        String dynFileName = "screenshot-" + System.currentTimeMillis() + "-" + fileName + ".png";
        // File ssPath = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\images\\" + fileName);
        File ssPath = new File(relativeToAbsolutePath("src/test/resources/images", dynFileName));

        try {
            ImageIO.write(screenshot.getImage(), "PNG", ssPath);
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return ssPath.toString();
    }

    // Reports, ExtentReport or Allure

    //static ExtentReports extent;


    public static ExtentReports getExtentReportObject() {
        // ExtentReports, ExtentSparkReporter
        //String path = System.getProperty("user.dir") + "\\src\\test\\resources\\reports\\extent_index.html";

        // gives you absolute path :
        String path = relativeToAbsolutePath("src/test/resources/reports", "extent_index.html");

        ExtentSparkReporter reporter = new ExtentSparkReporter(path);

        reporter.config().setReportName("Web Automation Test Results");
        reporter.config().setDocumentTitle("Web Automation Test Results");
        reporter.config().setTheme(Theme.STANDARD);

        reporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
        reporter.config().setEncoding("UTF-8");
        // reporter.config().setOfflineMode(true);

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Elektroyazilim");

        return extent;
    }

    private static Path createDirectory(String relativePath) { // src/test/resources/reports
        Path path = Paths.get(relativePath); // src\test\resources\reports
        if (!Files.exists(path)) // what if folder is not
        {
            try {
                Files.createDirectories(path);
            } catch (Exception ex) {
                System.out.println("Directory cannot created");
            }
        }
        return path;
    }

    // This method also check that if the directory is exist or not, if not it created
    public static String relativeToAbsolutePath(String relativePath, String fileName) // dynamic absolute path
    {
        final String OUTPUT_FOLDER = relativePath; // "src/test/resources/reports"; // relative path
        final String FILE_NAME = fileName; //"extent_index.html";
        Path halfAbsolutePath = createDirectory(OUTPUT_FOLDER); // src\test\resources\reports"
        String path = System.getProperty("user.dir") + "\\" + halfAbsolutePath + "\\" + FILE_NAME;
        return path;
    }

}

// relative path kabul etmiyorsa
// String destFile = System.getProperty("user.dir") + "\\outs\\"+testCaseName + ".png";