package amazon.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPageV1ByPriv {
    WebDriver driver = null;

    public LoginPageV1ByPriv(WebDriver driver) {
        this.driver = driver;
    }

    private By username = By.id("username");
    private By password = By.id("password");
    private By termChck = By.id("terms");
    private By signinBtn = By.id("signInBtn");


    public void enterUsername(String usernameText) {
        // control
        driver.findElement(username).sendKeys(usernameText);
    }

    public void enterPassword(String passwordText) {
        driver.findElement(password).sendKeys(passwordText);
    }

    public void clickTerms() {
        driver.findElement(termChck).click();
    }

    public void confirmButton() {
        // if element can be clickable
        driver.findElement(signinBtn).click();
    }

}
