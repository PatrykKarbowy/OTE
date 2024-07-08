package pages;

import locators.SearchResultPageLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class SearchResultPage extends BasicPage{

    @FindBy(css = SearchResultPageLocators.PRICE_RANGE_FROM_CSS_SELECTOR)
    private WebElement priceRangeFrom;

    @FindBy(css = SearchResultPageLocators.PRICE_RANGE_TO_CSS_SELECTOR)
    private WebElement priceRangeTo;

    @FindBy(css = SearchResultPageLocators.PRODUCTS_ON_PAGE_CSS_SELECTOR)
    private List<WebElement> productsOnPage;

    @FindBy(tagName = SearchResultPageLocators.PRODUCT_TITLE_TAG_NAME)
    private WebElement productTitle;

    @FindBy(css = SearchResultPageLocators.PRODUCT_PRICE_CSS_SELECTOR)
    private WebElement productPrice;

    @FindBy(css = SearchResultPageLocators.PRODUCT_LINK_CSS_SELECTOR)
    private WebElement productLink;

    @FindBy(css = SearchResultPageLocators.PRODUCT_LOCATION_DATE_CSS_SELECTOR)
    private WebElement productLocationDate;

    public SearchResultPage(WebDriver driver){
        super(driver);
    }

    public void setPriceRange(int priceFrom, int priceTo){
         wait.until(ExpectedConditions.visibilityOf(priceRangeFrom));
         priceRangeFrom.sendKeys(String.valueOf(priceFrom));
         wait.until(ExpectedConditions.visibilityOf(priceRangeTo));
         priceRangeTo.sendKeys(String.valueOf(priceTo));
    }


}
