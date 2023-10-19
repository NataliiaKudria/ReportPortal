package pages;

import static drivermanager.CustomWebDriverManager.getDriverManagerInstance;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage {
    public WebDriver driver() {
        return getDriverManagerInstance().getDriver();
    }
    public WebElement getElement(By elem) {
        return driver().findElement(elem);
    }
}
