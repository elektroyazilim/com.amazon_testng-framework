package amazon.tests;

import amazon.utils.Driver;
import amazon.utils.ExcelUtils;
import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ExcelDataCsvTest {

    @Test(dataProvider = "getAllDataFromCSV")
    public void loginDataTest(String username, String password)
    {
        Driver.getDriver().get("https://rahulshettyacademy.com/loginpagePractise/");
        Driver.getDriver().findElement(By.id("username")).sendKeys(username);
        Driver.getDriver().findElement(By.id("password")).sendKeys(password);

        Driver.getDriver().findElement(By.id("signInBtn")).click();

    }

    @DataProvider
    public Object[][] getAllDataFromCSV()
    {
        Object[][] data = ExcelUtils.getCsvData("data.csv", ",");
        // [[us1, pass1], [us2, pass2], [us3, pass3]]
        return data;
    }
}
