package steps;

import config.MainConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import pages.BasicPage;
import pages.SearchResultPage;
import utils.ExcelWriter;

public class SearchResultPageSteps extends BasicPage {
    private SearchResultPage searchResultPage = new SearchResultPage(driver);
    private static final Logger logger = LogManager.getLogger(SearchResultPageSteps.class);

    public SearchResultPageSteps(WebDriver driver) {
        super(driver);
    }

    public void setFilters(int priceFrom, int priceTo) {
        waitCertainPeriodOfTime(2);
        searchResultPage.setPriceRange(priceFrom, priceTo);
    }

    public void saveAllItemTextObjectsToExcel(int numberOfProductsToSave) {
        ExcelWriter excelWriter = new ExcelWriter((short) 12, true);
        excelWriter.saveSearchResultToExcelFile(MainConfig.FILE_NAME, MainConfig.SHEET_NAME, searchResultPage.getValuesFromSearchResult(numberOfProductsToSave));
        logger.info("Saved Excel with found products in: {}", MainConfig.FILE_NAME + ".xlsx");
    }

}
