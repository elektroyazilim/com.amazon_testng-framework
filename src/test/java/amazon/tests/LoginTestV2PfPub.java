package amazon.tests;

import amazon.pages.LoginPageV2PfPub;
import amazon.utils.BrowserUtils;
import amazon.utils.Driver;
import org.testng.annotations.Test;

public class LoginTestV2PfPub {

    LoginPageV2PfPub loginPage = new LoginPageV2PfPub();

    @Test
    public void loginPositive() {
        Driver.getDriver().get("https://rahulshettyacademy.com/loginpagePractise/");

        loginPage.username.sendKeys("rahulshettyacademy");
        loginPage.password.sendKeys("learning");
        loginPage.termChck.click();
        loginPage.signinBtn.click();

        BrowserUtils.waitFor(3);

        System.out.println("LoginTestV2PfPub.loginPositive : "+ Thread.currentThread().getId());
    }

}
