package steps;

import org.openqa.selenium.WebDriver;
import pages.MainPage;

public class MainPageSteps {
    private MainPage mainPage;
    private WebDriver driver;

    public MainPageSteps(WebDriver driver){
        this.driver = driver;
    }

    public void searchPhrase(String phrase, String location){
        mainPage = new MainPage(driver);
        mainPage.acceptCookies();
        mainPage.writeSearchPhrase(phrase);
        mainPage.writeSearchLocation(location);
        mainPage.searchResults();
    }
}
