package steps;

import org.openqa.selenium.WebDriver;
import pages.BasicPage;
import pages.SearchResultPage;

public class SearchResultPageSteps extends BasicPage {
    private SearchResultPage searchResultPage;

    public SearchResultPageSteps(WebDriver driver){
        super(driver);
    }

    public void setFilters(int priceFrom, int priceTo){
        searchResultPage.setPriceRange(priceFrom, priceTo);
    }

}
