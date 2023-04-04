package amazon.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchPage extends Base{

    @FindBy(css = "a span.a-color-base.a-text-normal")
    public List<WebElement> searchResults;

    @FindBy(css = "div#s-refinements")
    public WebElement filterSection;

    @FindBy(id = "reviewsRefinements")
    public WebElement reviewFilter;

    @FindBy(id = "brandsRefinements")
    public WebElement brandFilter;

    @FindBy(id = "priceRefinements")
    public WebElement priceFilter;

    @FindBy(id = "a-autoid-0-announce") //sort cbb
    public WebElement sortOptBtn;

    @FindBy(xpath = "//div[@class='a-section a-spacing-small a-spacing-top-small']")
    public WebElement numberOfSearchResult;

    @FindBy(css = "span.s-pagination-previous") // previous
    public WebElement previousBtn;

    @FindBy(xpath = "//*[contains(@class, 's-pagination-next')]")
    public WebElement nextBtn;

}
