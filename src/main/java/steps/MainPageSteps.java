package steps;

import org.openqa.selenium.WebDriver;
import pages.BasicPage;
import pages.MainPage;

public class MainPageSteps extends BasicPage {
    private MainPage mainPage;

    public MainPageSteps(WebDriver driver){
        super(driver);
    }

    public void searchPhrase(String phrase, String location){
        mainPage = new MainPage(driver);
        mainPage.acceptCookies();
        mainPage.writeSearchPhrase(phrase);
        mainPage.writeSearchLocation(location);
        mainPage.searchResults();
    }
}
