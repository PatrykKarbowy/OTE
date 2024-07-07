package worker;

import locators.MainPageLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import steps.MainPageSteps;
import utils.SearchConfig;

public class MainWorker {
    private WebDriver driver;
    private MainPageSteps mainPageSteps;
    private SearchConfig cfg;

    public MainWorker(SearchConfig cfg){
        this.cfg = cfg;
    }

    private WebDriver configureDriver(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        return driver;
    }

    public void start(){
        driver = configureDriver();
        driver.get(MainPageLocators.PAGE_URL);
        mainPageSteps = new MainPageSteps(driver);
        mainPageSteps.searchPhrase(cfg.getSearchPhrase(), cfg.getSearchLocation());
    }
}
