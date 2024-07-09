package pages;

import locators.SearchResultPageLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.SearchItemObject;
import utils.UtilsMethods;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchResultPage extends BasicPage{

    @FindBy(css = SearchResultPageLocators.PRICE_RANGE_FROM_CSS_SELECTOR)
    private WebElement priceRangeFrom;

    @FindBy(css = SearchResultPageLocators.PRICE_RANGE_TO_CSS_SELECTOR)
    private WebElement priceRangeTo;

    @FindBy(css = SearchResultPageLocators.PRODUCTS_ON_PAGE_CSS_SELECTOR)
    private List<WebElement> productsOnPage;

//    @FindBy(tagName = SearchResultPageLocators.PRODUCT_TITLE_TAG_NAME)
//    private WebElement productTitle;
//
//    @FindBy(css = SearchResultPageLocators.PRODUCT_PRICE_CSS_SELECTOR)
//    private WebElement productPrice;
//
//    @FindBy(css = SearchResultPageLocators.PRODUCT_LINK_CSS_SELECTOR)
//    private WebElement productLink;
//
//    @FindBy(css = SearchResultPageLocators.PRODUCT_LOCATION_DATE_CSS_SELECTOR)
//    private WebElement productLocationDate;

    public SearchResultPage(WebDriver driver){
        super(driver);
    }

    public void setPriceRange(int priceFrom, int priceTo){
         wait.until(ExpectedConditions.visibilityOf(priceRangeFrom));
         priceRangeFrom.sendKeys(String.valueOf(priceFrom));
         wait.until(ExpectedConditions.visibilityOf(priceRangeTo));
         priceRangeTo.sendKeys(String.valueOf(priceTo));
    }

    public List<SearchItemObject> getValuesFromSearchResult(){
        List<SearchItemObject> searchItemObjectList = new ArrayList<>();
        wait.until(ExpectedConditions.visibilityOfAllElements(productsOnPage));
        for (WebElement product : productsOnPage){
            String title = product.findElement(By.tagName(SearchResultPageLocators.PRODUCT_TITLE_TAG_NAME)).getText();
            String price = product.findElement(By.cssSelector(SearchResultPageLocators.PRODUCT_PRICE_CSS_SELECTOR)).getText();
            String link = product.findElement(By.cssSelector(SearchResultPageLocators.PRODUCT_LINK_CSS_SELECTOR)).getText();
            String locationDate = product.findElement(By.cssSelector(SearchResultPageLocators.PRODUCT_LOCATION_DATE_CSS_SELECTOR)).getText();

            float floatPrice = Float.parseFloat(price);

            Map<String, String> separatedLocationDate = UtilsMethods.getSeparatedDateLocation(locationDate);

            SearchItemObject itemObject = new SearchItemObject(floatPrice,title,link,separatedLocationDate.get("Date"),separatedLocationDate.get("City"));
            searchItemObjectList.add(itemObject);
        }
        return searchItemObjectList;
    }

}
