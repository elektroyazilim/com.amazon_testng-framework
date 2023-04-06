package amazon.tests;

import amazon.pages.LoginPageV2PfPriv;
import amazon.utils.BrowserUtils;
import amazon.utils.Driver;
import org.testng.annotations.Test;

public class LoginTestV2PfPriv {

    LoginPageV2PfPriv loginpage = new LoginPageV2PfPriv();

    @Test
    public void loginPositive() {

        Driver.getDriver().get("https://rahulshettyacademy.com/loginpagePractise/");

        loginpage.enterUsername("rahulshettyacademy"); //textbox
        loginpage.enterPassword("learning");    //textbox
        loginpage.acceptTerms(); // checkbox
        loginpage.confirmForm(); // signin button

        BrowserUtils.waitFor(3);

        System.out.println("LoginTestV2PfPriv.loginPositive : "+ Thread.currentThread().getId());
    }
}
