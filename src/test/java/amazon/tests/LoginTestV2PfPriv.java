package amazon.tests;

import amazon.pages.LoginPageV2PfPriv;
import amazon.utils.Driver;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTestV2PfPriv {

    LoginPageV2PfPriv loginpage = new LoginPageV2PfPriv();

    public LoginTestV2PfPriv() throws IOException {
    }

    @Test
    public void loginPositive() throws IOException {
        Driver.getDriver().get("https://rahulshettyacademy.com/loginpagePractise/");


        loginpage.enterUsername("rahulshettyacademy");
        loginpage.enterPassword("learning");
        loginpage.acceptTerms();
        loginpage.confirmForm();
    }
}
