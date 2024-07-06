package pages;

import locators.MainPageLocators;
import org.openqa.selenium.By;
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

    @FindBy(xpath = MainPageLocators.ACCEPT_COOKIES_BUTTON_XPATH)
    private WebElement acceptCookiesButton;

    public MainPage(WebDriver driver){
        super(driver);
    }

    public void acceptCookies(){
        wait.until(ExpectedConditions.visibilityOf(acceptCookiesButton));
        acceptCookiesButton.click();
    }

    public void writeSearchPhrase(String phrase){
        wait.until(ExpectedConditions.visibilityOf(searchBar));
        searchBar.sendKeys(phrase);
    }

    public void writeSearchLocation(String location){
        wait.until(ExpectedConditions.visibilityOf(searchLocation));
        searchLocation.sendKeys(location);
        WebElement searchLocationSuggestion = driver.findElement(By.xpath(MainPageLocators.SEARCH_LOCATION_SUGGESTION_XPATH));
        searchLocationSuggestion.click();
    }

    public void searchResults(){
        wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        searchButton.click();
    }

}
