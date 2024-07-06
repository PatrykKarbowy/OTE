package worker;

import locators.MainPageLocators;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import steps.MainPageSteps;

public class MainWorker {
    private WebDriver driver;
    private MainPageSteps mainPageSteps;

    private WebDriver configureDriver(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        return driver;
    }

    public void start(){
        driver = configureDriver();
        driver.get(MainPageLocators.PAGE_URL);
        mainPageSteps = new MainPageSteps();
    }
}
