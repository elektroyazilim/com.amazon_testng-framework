package amazon.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;

public class LoginPageV2PfPriv extends Base{

    @FindBy(id = "username")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "terms")
    private WebElement termChck;

    @FindBy(id = "signInBtn")
    private WebElement signinBtn;

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
        // control
        signinBtn.click();
    }

}
