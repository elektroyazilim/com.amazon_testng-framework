package amazon.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends Base {
    @FindBy(css = "div#navbar-backup-backup")
    public List<WebElement> fakeBar;

    @FindBy(id = "twotabsearchtextbox")
    public WebElement searchBox;

    @FindBy(id = "nav-search-submit-button")
    public WebElement searchButton;

    @FindBy(xpath = "//ul[@role='list']/li")
    public List<WebElement> products;

    @FindBy(xpath = "//ul[@role='list']/li[1]//a")
    public WebElement firstProductInHome;




}

