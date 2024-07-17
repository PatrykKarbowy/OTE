package pages;

import locators.SearchResultPageLocators;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    private static final Logger logger = LogManager.getLogger(SearchResultPage.class);

    @FindBy(css = SearchResultPageLocators.PRICE_RANGE_FROM_CSS_SELECTOR)
    private WebElement priceRangeFrom;

    @FindBy(css = SearchResultPageLocators.PRICE_RANGE_TO_CSS_SELECTOR)
    private WebElement priceRangeTo;

    @FindBy(css = SearchResultPageLocators.PRODUCTS_ON_PAGE_CSS_SELECTOR)
    private List<WebElement> productsOnPage;

    public SearchResultPage(WebDriver driver){
        super(driver);
    }

    public void setPriceRange(int priceFrom, int priceTo){
         wait.until(ExpectedConditions.visibilityOf(priceRangeFrom));
         priceRangeFrom.sendKeys(String.valueOf(priceFrom));
         wait.until(ExpectedConditions.visibilityOf(priceRangeTo));
         priceRangeTo.sendKeys(String.valueOf(priceTo));
    }

    public List<SearchItemObject> getValuesFromSearchResult(int numberOfReturnedProducts) throws InterruptedException{
        List<SearchItemObject> searchItemObjectList = new ArrayList<>();
        implicitlyWait(2);
        wait.until(ExpectedConditions.visibilityOfAllElements(productsOnPage));
        int numberOfProductsToReturn = numberOfReturnedProducts;

        if (numberOfProductsToReturn >= productsOnPage.size()){
            numberOfProductsToReturn = productsOnPage.size() - 1;
            logger.warn("Cannot find {}, found instead {} products",numberOfReturnedProducts, numberOfProductsToReturn);
        }

        for (int i=0; i<= numberOfProductsToReturn; i++){
            WebElement product = productsOnPage.get(i);
            String title = product.findElement(By.tagName(SearchResultPageLocators.PRODUCT_TITLE_TAG_NAME)).getText();
            String price = product.findElement(By.cssSelector(SearchResultPageLocators.PRODUCT_PRICE_CSS_SELECTOR)).getText();
            String link = product.findElement(By.tagName(SearchResultPageLocators.PRODUCT_LINK_TAG_NAME)).getAttribute("href");
            String locationDate = product.findElement(By.cssSelector(SearchResultPageLocators.PRODUCT_LOCATION_DATE_CSS_SELECTOR)).getText();

            float floatPrice = UtilsMethods.getPriceFromText(price);

            Map<String, String> separatedLocationDate = UtilsMethods.getSeparatedDateLocation(locationDate);

            SearchItemObject itemObject = new SearchItemObject(floatPrice,title,link,separatedLocationDate.get("Date"),separatedLocationDate.get("City"));
            searchItemObjectList.add(itemObject);
        }
        return searchItemObjectList;
    }

}
