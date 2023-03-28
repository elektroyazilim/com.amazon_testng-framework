package amazon.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPageV1ByPriv {
    WebDriver driver = null;

    public LoginPageV1ByPriv(WebDriver driver)
    {
        this.driver = driver;
    }

    By username = By.id("username");
    By password = By.id("password");
    By termChck = By.id("terms");
    By signinBtn = By.id("signInBtn");

    public void enterUsername(String usernameText)
    {
        driver.findElement(username).sendKeys(usernameText);
    }

    public void enterPassword(String passwordText)
    {
        driver.findElement(password).sendKeys(passwordText);
    }
    public void clickTerms()
    {
        driver.findElement(termChck).click();
    }
    public void confirmButton()
    {
        driver.findElement(signinBtn).click();
    }

}
