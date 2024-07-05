package worker;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MainWorker {
    private WebDriver driver;

    private WebDriver configureDriver(){
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver = new ChromeDriver();
        return driver;
    }

    public void start(){
        driver = configureDriver();
    }
}
