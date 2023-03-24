package amazon.tests;

import amazon.pages.HomePage;
import amazon.pages.LoginPageV1By;
import amazon.utils.Driver;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest {

    LoginPageV1By loginPage = new LoginPageV1By();
    HomePage homePage = new HomePage();

    @Test
    public void loginPositive() throws IOException {
        Driver.getDriver().get("https://rahulshettyacademy.com/loginpagePractise/");

        Driver.getDriver().findElement(loginPage.username).sendKeys("Elektroyazilim");
        Driver.getDriver().findElement(loginPage.password).sendKeys("123456");


    }
}
