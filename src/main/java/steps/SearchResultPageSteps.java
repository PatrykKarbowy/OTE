package steps;

import org.openqa.selenium.WebDriver;
import pages.BasicPage;
import pages.SearchResultPage;

public class SearchResultPageSteps extends BasicPage {
    private SearchResultPage searchResultPage = new SearchResultPage(driver);

    public SearchResultPageSteps(WebDriver driver){
        super(driver);
    }

    public void setFilters(int priceFrom, int priceTo) throws InterruptedException{
        implicitlyWait(2);
        searchResultPage.setPriceRange(priceFrom, priceTo);
    }
    public void saveAllItemTextObjectsToExcel(){

    }

}
