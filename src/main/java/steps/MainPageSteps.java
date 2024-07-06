package steps;

import org.openqa.selenium.WebDriver;
import pages.MainPage;

public class MainPageSteps {
    private MainPage mainPage;

    public void searchPhrase(String phrase, String location, WebDriver driver){
        mainPage = new MainPage(driver);
        mainPage.acceptCookies();
        mainPage.writeSearchPhrase(phrase);
        mainPage.writeSearchLocation(location);
        mainPage.searchResults();
    }
}
