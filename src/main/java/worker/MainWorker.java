package worker;

import config.MainConfig;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import steps.MainPageSteps;
import steps.SearchResultPageSteps;
import utils.SearchConfig;

public class MainWorker {
    private WebDriver driver;
    private MainPageSteps mainPageSteps;
    private SearchResultPageSteps searchResultPageSteps;
    private SearchConfig cfg;

    public MainWorker(SearchConfig cfg) {
        this.cfg = cfg;
    }

    private WebDriver configureDriver() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1400, 800));
        return driver;
    }

    public void start() {
        driver = configureDriver();
        driver.get(MainConfig.PAGE_URL);
        mainPageSteps = new MainPageSteps(driver);
        mainPageSteps.searchPhrase(cfg.getSearchPhrase(), cfg.getSearchLocation());
        searchResultPageSteps = new SearchResultPageSteps(driver);
        searchResultPageSteps.setFilters(cfg.getPriceFrom(), cfg.getPriceTo());
        searchResultPageSteps.saveAllItemTextObjectsToExcel(cfg.getSaveElements());
    }

    public void stop() {
        driver.quit();
    }
}
