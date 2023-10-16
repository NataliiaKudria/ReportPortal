package drivermanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CustomWebDriverManager {

    private WebDriver driver;
    private static CustomWebDriverManager driverManagerInstance = null;

    private CustomWebDriverManager() {
        this.driver = createDriver();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public static CustomWebDriverManager getDriverManagerInstance() {
        if (driverManagerInstance == null)
            driverManagerInstance = new CustomWebDriverManager();
        return driverManagerInstance;
    }

    private WebDriver createDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
        return driver;
    }
}

