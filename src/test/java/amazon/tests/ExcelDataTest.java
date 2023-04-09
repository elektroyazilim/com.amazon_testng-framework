package amazon.tests;

import amazon.utils.Driver;
import amazon.utils.ExcelUtils;
import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class ExcelDataTest {
    @Test(dataProvider = "dataFromExcelAllRows")
    public void loginDataTest(String username, String password) {

        Driver.getDriver().get("https://rahulshettyacademy.com/loginpagePractise/");
        Driver.getDriver().findElement(By.id("username")).sendKeys(username);
        Driver.getDriver().findElement(By.id("password")).sendKeys(password);

        Driver.getDriver().findElement(By.id("signInBtn")).click();
    }

    @DataProvider
    public Object[][] dataFromExcelAllRows() {
        List<List<String>> liste = ExcelUtils.getDataAllRows("data.xlsx", "pair");
        Object[][] data = new Object[liste.size()][liste.get(0).size()];

        // multidimensional list to object[][] array - liste -> data
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < liste.get(0).size(); j++) {
                data[i][j] = liste.get(i).get(j);
            }
        }
        return data;
    }
}


/*
    @DataProvider
    public Object[][] dataFromExcelAllRows() { // for all rows method
        List<List<String>> liste = ExcelUtils.getDataAllRows("data.xlsx", "pair");
        Object[][] data = new Object[liste.size()][liste.get(0).size()];

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < liste.get(0).size(); j++) {
                data[i][j] = liste.get(i).get(j);
            }

        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.println(data[i][j]);
            }
        }
        return data;
    }
 */