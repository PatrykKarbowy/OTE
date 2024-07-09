package steps;

import org.openqa.selenium.WebDriver;
import pages.BasicPage;
import pages.SearchResultPage;
import utils.SearchItemObject;

import java.util.List;

public class SearchResultPageSteps extends BasicPage {
    private SearchResultPage searchResultPage = new SearchResultPage(driver);

    public SearchResultPageSteps(WebDriver driver){
        super(driver);
    }

    public void setFilters(int priceFrom, int priceTo) throws InterruptedException{
        implicitlyWait(2);
        searchResultPage.setPriceRange(priceFrom, priceTo);
        List<SearchItemObject> itemList = searchResultPage.getValuesFromSearchResult();
        System.out.println(itemList.get(0));
    }
    public void saveAllItemTextObjectsToExcel(){

    }

}
