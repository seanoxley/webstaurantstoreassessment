package pageObjects.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestPageBase {

    /**
     * Driver instance.
     */
    public static WebDriver Driver;

    public void SetupDriver(String browser) {
        // Use locally packaged chromedriver.
        System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");

        ChromeOptions chromeOptions = new ChromeOptions();

        // I am using Brave locally so I am directing the web driver to use that binary.
        // This is handled via a command line argument. The default browser is Chrome.
        if (browser.equals("brave")) {
            chromeOptions.setBinary("C:/Program Files/BraveSoftware/Brave-Browser/Application/brave.exe");
        }

        // Setup the web driver before each test.
        WebDriverManager.chromedriver().setup();
        Driver = new ChromeDriver(chromeOptions);
        Driver.manage().deleteAllCookies();
        Driver.manage().window().maximize();
    }

    public void TearDownDriver() {
        Driver.quit();
    }

    public void OpenUrl(String url) {
        Driver.get(url);
    }

    public boolean waitUntilWebElementisDisplayed(WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(Driver, Duration.ofSeconds(5));
        return wait.until(e -> webElement.isDisplayed());
    }

    public boolean isWebElementDisplayed(WebElement webElement) {
        return webElement.isDisplayed();
    }
}
