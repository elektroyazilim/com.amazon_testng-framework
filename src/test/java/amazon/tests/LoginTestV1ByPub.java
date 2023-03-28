package amazon.tests;

import amazon.pages.LoginPageV1ByPub;
import amazon.utils.Driver;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTestV1ByPub {

    LoginPageV1ByPub loginPage = new LoginPageV1ByPub();

    @Test
    public void loginPositive() throws IOException {

        Driver.getDriver().get("https://rahulshettyacademy.com/loginpagePractise/");

        Driver.getDriver().findElement(By.id("username")).sendKeys("rahulshettyacademy");
        Driver.getDriver().findElement(By.id("password")).sendKeys("learning");
        Driver.getDriver().findElement(By.id("terms")).click();
        Driver.getDriver().findElement(By.id("signInBtn")).click();
    }

    @Test
    public void logicPositiveByStylePublic() throws IOException {
        Driver.getDriver().get("https://rahulshettyacademy.com/loginpagePractise/");
        Driver.getDriver().findElement(loginPage.username).sendKeys("rahulshettyacademy");
        Driver.getDriver().findElement(loginPage.password).sendKeys("learning");
        Driver.getDriver().findElement(loginPage.termChck).click();
        Driver.getDriver().findElement(loginPage.signinBtn).click();
    }
}
