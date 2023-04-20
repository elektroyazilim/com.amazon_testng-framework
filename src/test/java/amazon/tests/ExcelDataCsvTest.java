package amazon.tests;

import amazon.pages.POManager;
import amazon.utils.Driver;
import amazon.utils.ExcelUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ExcelDataCsvTest {
    POManager pom = new POManager();

    @Test(dataProvider = "getAllDataFromCSV")
    public void loginDataTest(String username, String password)
    {
        Driver.getDriver().get("https://rahulshettyacademy.com/loginpagePractise/");
        pom.getLoginPage().enterUsername(username);
        pom.getLoginPage().enterPassword(password);
        pom.getLoginPage().confirmLogin();
    }

    @DataProvider
    public Object[][] getAllDataFromCSV()
    {
        Object[][] data = ExcelUtils.getCsvData("data.csv", ",");
        // [[us1, pass1], [us2, pass2], [us3, pass3]]
        return data;
    }
}
