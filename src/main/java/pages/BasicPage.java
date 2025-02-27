package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasicPage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasicPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void implicitlyWait(int numberOfSeconds) throws InterruptedException {
        synchronized (driver) {
            driver.wait(numberOfSeconds * 1000);
        }
    }
}
