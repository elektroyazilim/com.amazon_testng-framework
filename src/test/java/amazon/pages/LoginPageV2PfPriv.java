package amazon.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;

public class LoginPageV2PfPriv extends Base{

    @FindBy(id = "username")
    WebElement username;

    @FindBy(id = "password")
    WebElement password;

    @FindBy(id = "terms")
    WebElement termChck;

    @FindBy(id = "signInBtn")
    WebElement signinBtn;

    public void enterUsername(String strUsername)
    {
        username.sendKeys(strUsername);
    }


    public void enterPassword(String strPassword)
    {
        password.sendKeys(strPassword);
    }


    public void acceptTerms()
    {
        termChck.click();
    }


    public void confirmForm()
    {
        signinBtn.click();
    }

}
