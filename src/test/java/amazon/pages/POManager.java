package amazon.pages;

public class POManager {

    private HomePage homePage;
    private LoginPage loginPage;
    private SearchPage searchPage;


    public HomePage getHomePage() {
        return homePage == null ? new HomePage() : homePage;
    }
    public LoginPage getLoginPage() {

        return loginPage == null ? new LoginPage() : loginPage;
    }
    public SearchPage getSearchPage() {

        return searchPage == null ? new SearchPage() : searchPage;
    }
}
