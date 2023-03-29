package amazon.pages;

import amazon.utils.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class LoginPageV2PfPub extends Base {

    @FindBy(id = "username")
    public WebElement username;

    @FindBy(id = "password")
    public WebElement password;

    @FindBy(id = "terms")
    public WebElement termChck;

    @FindBy(id = "signInBtn")
    public WebElement signinBtn;

}
