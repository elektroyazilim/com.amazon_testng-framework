package amazon.tests;

import amazon.pages.LoginPageV2PfPub;
import amazon.utils.Driver;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTestV2PfPub {

    LoginPageV2PfPub loginPage = new LoginPageV2PfPub();

    public LoginTestV2PfPub() throws IOException {
    }

    @Test
    public void loginPositive() throws IOException {
        Driver.getDriver().get("https://rahulshettyacademy.com/loginpagePractise/");

        loginPage.username.sendKeys("rahulshettyacademy");
        loginPage.password.sendKeys("learning");
        loginPage.termChck.click();
        loginPage.signinBtn.click();

    }



}
