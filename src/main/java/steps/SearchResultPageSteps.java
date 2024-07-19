package steps;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import pages.BasicPage;
import pages.SearchResultPage;
import utils.ExcelWriter;
import utils.SearchConfig;

public class SearchResultPageSteps extends BasicPage {
    private SearchResultPage searchResultPage = new SearchResultPage(driver);
    private static final Logger logger = LogManager.getLogger(SearchResultPageSteps.class);

    public SearchResultPageSteps(WebDriver driver){
        super(driver);
    }

    public void setFilters(int priceFrom, int priceTo) throws InterruptedException{
        implicitlyWait(2);
        searchResultPage.setPriceRange(priceFrom, priceTo);
    }
    public void saveAllItemTextObjectsToExcel(int numberOfProductsToSave) throws InterruptedException{
        ExcelWriter excelWriter = new ExcelWriter((short)12, true);
        excelWriter.saveSearchResultToExcelFile(SearchConfig.FILE_NAME,SearchConfig.SHEET_NAME,searchResultPage.getValuesFromSearchResult(numberOfProductsToSave));
        logger.info("Saved Excel with found products in: {}",SearchConfig.FILE_NAME+".xlsx");
    }

}
