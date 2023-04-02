package amazon.utils;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;


import java.time.Duration;

public class Driver {
    // Eğer bir class'tan NESNE ÜRETİLMESİNİ İSTEMİYORSANIZ
    // constructor'ı private yapabilirsiniz (Singleton Class)
    private Driver() {
    }

    // WebDriver nesnemizi, static olarak oluşturduk, çünkü program başlar başlamaz
    // hafızada yer almasını istiyoruz.
    static WebDriver driver;
    // Programın herhangi bir yerinden getDriver() methodu çağırılarak
    // hafıza STATIC olarak oluşturulmuş DRIVER nesnesine erişebiliriz.
    // Yani yeniden WebDriver nesnesi oluşturmak zorunda değiliz.
    //Driver.getDriver()
    public static WebDriver getDriver() {
        // Eğer driver nesnesi hafızada boşsa, oluşturulmamışsa yeniden oluşturmana gerek yok.
        // Eğer null ise, yeniden oluşturabilirsin.
        // Sadece ilk çağırıldığında bir tane nesne üret, sonraki çağırmalarda var olan nesnesi kullan.
        if (driver == null) {
            switch (Config.getProperty("browser")) {
                case "chrome":
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                case "edge":
                    driver = new EdgeDriver();
                    break;
                case "safari":
                    driver = new SafariDriver();
                    break;
                case "headless-chrome":
                    driver = new ChromeDriver(new ChromeOptions().setHeadless(true));
                    break;
            }
            driver.manage().window().maximize(); //-> not move aanywhere
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().deleteAllCookies();

        return driver;
    }

    public static void closeDriver() {
        // Eğer driver nesnesi NULL değilse, yani hafızada varsa
        if (driver != null) {
            driver.quit();  // driver'ı kapat
            driver = null;  // driver'ı hafızadan temizle.
        }
    }
}

/*
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
 */

