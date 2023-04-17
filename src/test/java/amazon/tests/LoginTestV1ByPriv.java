package amazon.tests;

import amazon.pages.LoginPageV1ByPriv;
import amazon.utils.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class LoginTestV1ByPriv {

    LoginPageV1ByPriv loginPage = new LoginPageV1ByPriv(Driver.getDriver());

    @Test()
    public void positiveLogin(){

        Driver.getDriver().get("https://rahulshettyacademy.com/loginpagePractise/");

        loginPage.enterUsername("rahulshettyacademy"); // username textbox
        loginPage.enterPassword("learning");
        loginPage.clickTerms(); // checkbox
        loginPage.confirmButton(); // confirm form

        System.out.println("LoginTestV1ByPriv.positiveLogin : "+ Thread.currentThread().getId());
    }

    // TestNG nin bu ozelligi ile XML olmadan tek bir testi birden fazla ayni typte daki (multithread)
    // browser da kosturabiliyoruz. Parallel run for only one test
    //@Test(threadPoolSize = 2, invocationCount = 2) // threadPoolSize = 2, invocationCount = 2, timeOut = 5000
    public void threadTest(){
        // her bir satırı 2 kez calistirarak ilerliyor, 2 kez rahul yaziyor, 2 kez checkbox a tikliyor gb..
        // tek driver imiz oldugundan anlamli degil

        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/loginpagePractise/");

        driver.findElement(By.id("username")).sendKeys("rahulshettyacademy"); // username textbox
        driver.findElement(By.id("password")).sendKeys("learning");

        driver.findElement(By.id("terms")).click(); // checkbox
        driver.findElement(By.id("signInBtn")).click();
        System.out.println("LoginTestV1ByPriv.positiveLogin : "+ Thread.currentThread().getId());
    }


}

// System.out.println("The thread ID for Firefox is "+ Thread.currentThread().getId());
