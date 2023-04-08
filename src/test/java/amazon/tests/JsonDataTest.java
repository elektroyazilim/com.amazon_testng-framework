package amazon.tests;

import amazon.utils.Driver;
import amazon.utils.JsonUtils;
import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

public class JsonDataTest {

    @Test(dataProvider = "getDataFromJson")
    public void jsonLoginTest(String username, String password)
    {
        Driver.getDriver().get("https://rahulshettyacademy.com/loginpagePractise/");
        Driver.getDriver().findElement(By.id("username")).sendKeys(username);
        Driver.getDriver().findElement(By.id("password")).sendKeys(password);

        Driver.getDriver().findElement(By.id("signInBtn")).click();
    }

    @DataProvider
    public Object[][] getDataFromJson()
    {
        List<HashMap<String, String>> data = JsonUtils.getJsonData("data.json");
        // [{password=rahull, username=Esma}, {password=shetty, username=Fatih}]

        Object[][] objData = new Object[data.size()][data.get(0).size()];

        // hashmap to object[][]
        for(int i =0; i< data.size(); i++)
        {
            for (int j =0; j < data.get(0).size(); j++)
            {
                if(j == 0)
                {
                    objData[i][j] = data.get(i).get("username");
                }
                else
                {
                    objData[i][j] = data.get(i).get("password");
                }

            }
        }

        return objData;

    }
}