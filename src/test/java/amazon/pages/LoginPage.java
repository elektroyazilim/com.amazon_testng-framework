package amazon.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends Base {

    @FindBy(id = "username")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "signInBtn")
    private WebElement signInBtn;

    public void enterUsername(String strUsername) {
        username.sendKeys(strUsername);
    }

    public void enterPassword(String passwrd) {
        password.sendKeys(passwrd);
    }

    public void confirmLogin() {
        if (signInBtn.isEnabled()) {
            signInBtn.click();
        }
    }

}
