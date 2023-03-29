package amazon.tests;

import amazon.pages.LoginPageV1ByPriv;
import amazon.utils.Driver;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTestV1ByPriv {

    LoginPageV1ByPriv loginPage = new LoginPageV1ByPriv(Driver.getDriver());

    @Test
    public void positiveLogin(){

        Driver.getDriver().get("https://rahulshettyacademy.com/loginpagePractise/");

        loginPage.enterUsername("rahulshettyacademy"); // username textbox
        loginPage.enterPassword("learning");
        loginPage.clickTerms(); // checkbox
        loginPage.confirmButton(); // confirm form
    }
}