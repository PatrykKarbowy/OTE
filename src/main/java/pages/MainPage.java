package pages;

import locators.MainPageLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends BasicPage {

    @FindBy(xpath = MainPageLocators.SEARCH_BAR_XPATH)
    private WebElement searchBar;

    @FindBy(xpath = MainPageLocators.SEARCH_LOCATION_XPATH)
    private WebElement searchLocation;

    @FindBy(xpath = MainPageLocators.SEARCH_BUTTON_XPATH)
    private WebElement searchButton;

    @FindBy(xpath = MainPageLocators.SEARCH_LOCATION_SUGGESTION_XPATH)
    private WebElement searchLocationSuggestion;

    public MainPage(WebDriver driver){
        super(driver);
    }

    public void writeSearchPhrase(String phrase){
        wait.until(ExpectedConditions.elementToBeClickable(searchBar));
        searchBar.sendKeys(phrase);
    }

    public void writeSearchLocation(String location){
        wait.until(ExpectedConditions.elementToBeClickable(searchLocation));
        searchLocation.sendKeys(location);
    }

    public void searchResults(){
        wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        searchButton.click();
    }

}
