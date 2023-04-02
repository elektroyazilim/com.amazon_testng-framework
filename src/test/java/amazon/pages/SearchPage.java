package amazon.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchPage extends Base{

    @FindBy(css = "a span.a-color-base.a-text-normal")
    public List<WebElement> searchResults;

}
