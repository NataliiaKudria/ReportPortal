package steps;

import drivermanager.CustomWebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.LoginPage;

public class BaseSteps {

    private final CustomWebDriverManager driverManager = new CustomWebDriverManager();
    private final WebDriver driver = driverManager.getDriver();

    public LoginPage loginPage() {
        return new LoginPage();
    }

    public WebElement getElement(By elem) {
        return driver.findElement(elem);
    }

    public CustomWebDriverManager getDriverManager() {
        return this.driverManager;
    }

}
